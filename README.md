# mobile-

## Retrofit + GSON

To correcly make requests and parse objects to java objects you might create the model for example

```java

public class User {
    private String name;
    private String sirname;
    private String facebookToken;
    private int age;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sirname='" + sirname + '\'' +
                ", facebookToken='" + facebookToken + '\'' +
                ", age=" + age +
                '}';
    }
}
```

then create an repository

```java
public interface Userrepo {

    @GET(USER)
    Call<User> getUser();

}
```

And finally the interactor

```java
public class Userinteractor {

    public static Call<User> getUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                                .create()
                ))
                .build();

        Userrepo userrepo= retrofit.create(Userrepo.class);
        return userrepo.getUser();
    }
}
```

given these steps, you will get something like

```json
{
  "name" : "Santiago",
  "facebook_token" : "asdasdasdtumama",
  "sirname" : "Lazzari",
  "age" : 22
}

```

And correcly parce it as a java object without implementing any parser.

## Mock Server

To correcly run the mock server run


```bash
cd MockServer && node index.js
```

to setup a custom response you might enter the following code in JS in the index.js file

```javascript
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

```

In this example, if the id query is 1 then will return the first json object, if is any other number, then will return a de other one.

The line with the sleep command is to give a latency of 2 secconds to make the operation making it more realistic.
