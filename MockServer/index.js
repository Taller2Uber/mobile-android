// Setup

var express = require('express');
var app = express();

app.use(express.static('public'));


// @GET
app.get('/', function (req, res) {
  res.send('Hello World!');
});

app.get('/wiki', function (req, res) {
  wiki = {data_one : 1,
          data_two : "dataTwo"}

  res.send(wiki)
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

app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});
