// Setup

var express = require('express');
var sleep = require('sleep');
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

  user = {
    "_id": {
      "$oid": "59de7b5704599c00083f2e17"
    },

    "fb_id": "141700183238421",
    "fb_token": "EAALQMNov0CkBAI2zaUK6swXQF1MfI8EQI2OaBqvGzlcKOST2Bv6irk4gLnrNrp0oiRCF6nXY4fLHmCvmm4s5IXApLyMeSlZCCwlVUDlkxN7NRUWPGLimvv7r8yCryc9yQQiy3irS5TF5KlwMVucRkfwIFfZAr0KdlzOifdfBu1ZAsZAWon7eiZB0XQrIHb8GPrF3RxnM4xgFdowZAcgzrYsHNuLKqZA9ZAsGJgUZCZAusJJQZDZD",
    "name": "Open Graph Test User",
    "gender": "female",

    "latitude": "null",
    "longitude": "null",
    "card": {
      "number": "123123123123",
      "expiration_date": "2017-10-11T19:19:32.009Z",
      "company": "VISA"
    }
}
  res.send(user)
});


app.post("/api/v1/drivers", function (req, res) {

  user = {
    "_id": {
      "$oid": "59de7b5704599c00083f2e17"
    },

    "fb_id": "141700183238421",
    "fb_token": "EAALQMNov0CkBAI2zaUK6swXQF1MfI8EQI2OaBqvGzlcKOST2Bv6irk4gLnrNrp0oiRCF6nXY4fLHmCvmm4s5IXApLyMeSlZCCwlVUDlkxN7NRUWPGLimvv7r8yCryc9yQQiy3irS5TF5KlwMVucRkfwIFfZAr0KdlzOifdfBu1ZAsZAWon7eiZB0XQrIHb8GPrF3RxnM4xgFdowZAcgzrYsHNuLKqZA9ZAsGJgUZCZAusJJQZDZD",
    "name": "Open Graph Test User",
    "gender": "female",
    "latitude": "null",
    "longitude": "null",
    "cars": [
      {
        "model": "A1",
        "brand": "Audi",
        "year": 2017,
        "license_plate": "eep410",
        "ac": true
      }
    ]
  }
  res.send(user)
});

app.post("/api/v1/drivers", function (req, res) {

  user = {
    "_id": {
      "$oid": "59de7b5704599c00083f2e17"
    },

    "fb_id": "141700183238421",
    "fb_token": "EAALQMNov0CkBAI2zaUK6swXQF1MfI8EQI2OaBqvGzlcKOST2Bv6irk4gLnrNrp0oiRCF6nXY4fLHmCvmm4s5IXApLyMeSlZCCwlVUDlkxN7NRUWPGLimvv7r8yCryc9yQQiy3irS5TF5KlwMVucRkfwIFfZAr0KdlzOifdfBu1ZAsZAWon7eiZB0XQrIHb8GPrF3RxnM4xgFdowZAcgzrYsHNuLKqZA9ZAsGJgUZCZAusJJQZDZD",
    "name": "Open Graph Test User",
    "gender": "female",
    "latitude": "null",
    "longitude": "null",
    "cars": [
      {
        "model": "A1",
        "brand": "Audi",
        "year": 2017,
        "license_plate": "eep410",
        "ac": true
      }
    ]
  }
  res.send(user)
});

app.post("/api/v1/passengers", function (req, res) {

  user = {
    "_id": {
      "$oid": "59de7b5704599c00083f2e17"
    },

    "fb_id": "141700183238421",
    "fb_token": "EAALQMNov0CkBAI2zaUK6swXQF1MfI8EQI2OaBqvGzlcKOST2Bv6irk4gLnrNrp0oiRCF6nXY4fLHmCvmm4s5IXApLyMeSlZCCwlVUDlkxN7NRUWPGLimvv7r8yCryc9yQQiy3irS5TF5KlwMVucRkfwIFfZAr0KdlzOifdfBu1ZAsZAWon7eiZB0XQrIHb8GPrF3RxnM4xgFdowZAcgzrYsHNuLKqZA9ZAsGJgUZCZAusJJQZDZD",
    "name": "Open Graph Test User",
    "gender": "female",

    "latitude": "null",
    "longitude": "null",
    "card": {
      "number": "123123123123",
      "expiration_date": "2017-10-11T19:19:32.009Z",
      "company": "VISA"
    }
}
  res.send(user)
});

app.listen(3000, function () {
  console.log('Mock server initialized in port 3000!');
});
