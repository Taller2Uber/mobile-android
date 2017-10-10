// Setup

var express = require('express');
var sleep = require('sleep');
var app = express();
var bodyParser = require('body-parser');


app.use(express.static('public'));
app.use(bodyParser);


// @GET
app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.get('/wiki', function (req, res) {
  wiki = {data_one : 1,
          data_two : "dataTwo"}

  res.send(wiki)
});

app.get('/api/user', function (req, res) {
  sleep.sleep(2);

  user = {}

  console.log(req)

  if (req.query.id == 1) {
    user = {name : "Santiago",
            facebook_token : "asdasdasdtumama",
            sirname : "Lazzari",
            age : 22
          }
  } else {
    user = {name : "Luciano",
            facebook_token : "asdasdasdtumama",
            sirname : "Lazzari",
            age : 10000
          }
  }
  console.log("GET USER : " + user)

  res.send(user)
});

// app.get("/image_id/:imageId/density/:density", function (req, res) {
//   var options = {
//     root: __dirname + '/public/',
//     dotfiles: 'deny',
//     headers: {
//         'x-timestamp': Date.now(),
//         'x-sent': true
//     }
//   };
//
//   var fileName = req.params.density + "/" + req.params.imageId + ".png";
//
//   res.sendFile(fileName, options, function (err) {
//     if (err) {
//       console.log(err);
//       res.status(err.status).end();
//     }
//     else {
//       console.log('Sent:', fileName);
//     }
//   });
// });

// get request to update images
app.get("/update_images", function (req, res) {
  var response = {response:[3, 4, 5]}

  console.log("Sent updatable images " + response);
  res.send(response)
});



// Realistic api

// Login a user with the facebook token (fb_token)

app.post("/api/v1/users/login", function (req, res) {
  body = res.body;

  console.log(body)
  res.send()
});



app.listen(3000, function () {
  console.log('Mock server initialized in port 3000!');
});
