# Prime Number Generator
This application is built to generate prime numbers between given range for a SimScale interview.
It has two different parts, with the first being a terminal application and the second being a web application.

For this application, I wrote five different prime number generators using different algorithms. There algorithms are
as follows:

- [Trial & Division](http://mathworld.wolfram.com/TrialDivision.html): The most basic algorithm.
- [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes): A little more complex than Trial & Division, but still rather simple.
- [Segmented Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Segmented_sieve): A different version of Sieve algorithm, mainly used for memory requirements.
- [Miller-Rabin](https://en.wikipedia.org/wiki/Miller–Rabin_primality_test): A probabilistic algorithm.
- [Heuristic Algorithm using Miller-Rabin and Fermat Primality Tests](https://en.wikipedia.org/wiki/Fermat_primality_test): Combination of two probabilistic primality test algorithms.

## Preparing Database

I prepared a MySQL script to prepare the database, which can be found on the root directory of the repository under the name of `create_schema.sql`. One should change `springuser` (two occurrences) and `ThePassword` according to their preference.

This script can be run from the command line by:

`sudo mysql < create_schema.sql`

The file `src/main/resources/application.properties` should also be edited if the username and password in the SQL script is changed by the user.

## Running the Application

### Command Line Application

To build the application as a command line application:

`gradle build -DjarName=prime-number-generator-cmd -DmainClass=com.simscale.terminal.Main`

After that, the application can be ran by:

```sh
cd build/libs/
java -jar prime-number-generator-cmd-1.0-SNAPSHOT.jar algorithmType start end
```

- algorithmType can be one of: `trial`, `sieve`, `segmented`, `miller`, `heuristic`
- start and end should be integers and end should be larger than start.

### Web Application

Make sure that SQL script was ran before running the web application and the database configuration is done right. MySQL on the computer should be active.

```sh
gradle build -DjarName=prime-number-generator-webapp -DmainClass=com.simscale.webapp.Application
cd build/libs/
java -cp . -jar prime-number-generator-webapp-1.0-SNAPSHOT.jar
```

The application will run on `localhost:8080`.

#### Endpoint

##### primes/ (POST)

Generates the prime numbers with the algorithm and range of your choice.

*Request Body*:

```json
{
  "start":10,
  "end": 20,
  "algorithmType": "trial"
}
```

*Example Response*:
```json
[
  11,
  13,
  17,
  19
]
```

#### Example of Table

![All requests in the table](https://i.imgur.com/yHVe1aR.png)

### Tests

Unit tests can be found under `src/test/java/com/simscale/algorithms` for all five algorithms with four test cases each.