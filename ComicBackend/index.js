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
app.get("/comic", (red, res, next)=>{
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

//Start server
app.listen(3000, ()=>{
		console.log('Comic API is running on port 3000');
	}
)