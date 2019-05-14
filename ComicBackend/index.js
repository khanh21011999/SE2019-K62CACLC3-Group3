/*
RESTFul Services by NodeJS
Author: Group 3
Update:
*/

var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser')

// connect to MySQL
var con = mysql.createConnection(
	{
		host: 'localhost',
		user: 'root',
		password: '', //Default password is blank
		database: 'test'
	}
);


require('events').EventEmitter.defaultMaxListeners=20; // Fix memory leaked

//Create RESTFul
var	app=express();
var publicDir = (__dirname + '/public/'); //Set static direction for display image local by url
app.use(express.static(publicDir));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

//Get all banner
app.get("/banner", (red, res, next)=>{
		con.query('SELECT * FROM banner', function(error, result, fields){
			con.on('error', function(err){
				console.log('[MY SQL ERROR]', err);
			});

			if (result && result.length){
				res.end(JSON.stringify(result));
			}
			else{
					res.end(JSON.stringify("Banner does not available"));
				}
			}
		)
	}
);

//Get all comic
app.get("/comic", (req, res, next)=>{
		con.query('SELECT * FROM manga', function(error, result, fields){
			con.on('error', function(err){
				console.log('[MY SQL ERROR]', err);
			});

			if (result && result.length){
				res.end(JSON.stringify(result));
			}
			else{
					res.end(JSON.stringify("Comic does not available"));
				}
			}
		)
	}
);

//Get chapter by comic ID
app.get("/chapter/:mangaid", (req, res, next)=>{
		con.query('SELECT * FROM chapter where MangaId=?', [req.params.mangaid], function(error, result, fields){
			con.on('error', function(err){
				console.log('[MY SQL ERROR]', err);
			});

			if (result && result.length){
				res.end(JSON.stringify(result));
			}
			else{
					res.end(JSON.stringify("Chapter does not available"));
				}
			}
		)
	}
);

//Get image by chapter ID
app.get("/links/:chapterid", (req, res, next)=>{
		con.query('SELECT * FROM link where ChapterId=?', [req.params.chapterid], function(error, result, fields){
			con.on('error', function(err){
				console.log('[MY SQL ERROR]', err);
			});

			if (result && result.length){
				res.end(JSON.stringify(result));
			}
			else{
					res.end(JSON.stringify("Chapter does not available"));
				}
			}
		)
	}
);

//Get all category
app.get("/categories", (req, res, next)=>{
		con.query('SELECT * FROM Category', function(error, result, fields){
			con.on('error', function(err){
				console.log('[MY SQL ERROR]', err);
			});

			if (result && result.length){
				res.end(JSON.stringify(result));
			}
			else{
					res.end(JSON.stringify("Category does not available"));
				}
			}
		)
	}
);

//Get all category
app.post("/filter", (req, res, next)=>{
		var post_data = req.body; // GET POST DATA from POST REQUEST
		var array = JSON.parse(post_data.data); // Parse 'data' field from POST REQUEST TO JSON ARRAY
		var query = " SELECT * FROM manga WHERE ID IN (SELECT MangaID FROM mangacategory"; // default query
		if (array.length > 0) {
		    query += "GROUP BY MangaID";
		    if (array.length == 1) // if user just submit 1 category
		        query+= "HAVING SUM(CASE WHEN CategoryID = "+array[0]+" THEN 1 ELSE 0 END) > 0)";
		    else {
		        for (var i=0; i<array.length; i++)
		        {
		            if (i==0) // first condition
		                query += "HAVING SUM(CASE WHEN CategoryID = "+array[0]+" THEN 1 ELSE 0 END) > 0 AND";
		            else if (i == array.length -1) // last condition
		                query += "SUM(CASE WHEN CategoryID ="+array[i]+" THEN 1 ELSE 0 END) >0)";
		            else
		                query += "SUM(CASE WHEN CategoryID ="+array[i]+" THEN 1 ELSE 0 END) >0 AND";
		        }
		    }

		    con.query(query, function(error, result, fields){
            			con.on('error', function(err){
            				console.log('[MY SQL ERROR]', err);
            			});

            			if (result && result.length){
            				res.end(JSON.stringify(result));
            			}
            			else{
            					res.end(JSON.stringify("Comic does not available"));
            				}
            			}
            		)
		}
	}
);
//search
app.post("/search",(req,res,next)=>{
	var post_data=req.body; //get body post
	var name_search=post_data.search;  //get 'search' data from post request

	var query= "SELECT * FROM manga WHERE Name Like'%"+name_search+"%'";
	con.query(query, function(error, result, fields){
						con.on('error', function(err){
							console.log('[MY SQL ERROR]', err);
						});

						if (result && result.length){
							res.end(JSON.stringify(result));
						}
						else{
								res.end(JSON.stringify("Comic does not available"));
							}
						}
					)
});
//Start server
app.listen(3000, ()=>{
		console.log('Comic API is running on port 3000');
	}
)
