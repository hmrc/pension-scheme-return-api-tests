**This is a template README.md.  Be sure to update this with project specific content that describes your api test project.**

# pension-scheme-return-api-tests
API test suite for the `Pension Scheme Return` using ScalaTest and [play-ws](https://github.com/playframework/play-ws) client.  

## Running the tests

Prior to executing the tests ensure you have:
 - Installed [MongoDB](https://docs.mongodb.com/manual/installation/) 
 - Installed/configured [service manager](https://github.com/hmrc/service-manager).  

Run the following commands to start services locally:

    docker run --rm -d --name mongo -d -p 27017:27017 mongo:4.0
    sm --start IVHO -r --wait 100

Using the `--wait 100` argument ensures a health check is run on all the services started as part of the profile. `100` refers to the given number of seconds to wait for services to pass health checks.    

## run api zap tests locally 
execute the shell script `run_zap_tests.sh` locally, this will download and export run the local zap and run all api and zap tests locally generates report

## run api tests locally
Then execute the `run_tests.sh` script:
edit the below shell script and add `-Dsecurity.assessment=false`

`./run_tests.sh <environment>` 

## run api tests against a test environment
The tests default run on local environment and docker local only. To run on other environments can refer to `src/test/resources/application.conf`


## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
