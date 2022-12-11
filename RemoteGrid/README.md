The tests are executed on [HyperExecute](https://lambdatest.com/hyperexecute) test automation & test orchestration platform offered by [LambdaTest](https://www.lambdatest.com/). It is recommended to have a look at [HyperExecute Support Documentation](https://www.lambdatest.com/support/docs/deep-dive-into-hyperexecute-yaml) before you start looking into the YAML used in the project.

## Basic Know-How of HyperExecute

HyperExecute is a smart test orchestration platform to run end-to-end tests at the fastest speed possible. HyperExecute lets you achieve an accelerated time to market by providing a test infrastructure that offers optimal speed, test orchestration, and detailed execution logs.

The overall experience helps teams test code and fix issues at a much faster pace. HyperExecute is configured using a YAML file. Instead of moving the Hub close to you, HyperExecute brings the test scripts close to the Hub!

Here are some of the documents (in chronological order) that can help you get started with HyperExecute:

- [HyperExecute - Getting Started](https://www.lambdatest.com/support/docs/getting-started-with-hyperexecute/)
- [HyperExecute - Key Features](https://www.lambdatest.com/support/docs/key-features-of-hyperexecute)
- [HyperExecute - What is different from Traditional Grids](https://www.lambdatest.com/support/docs/hyperexecute-vs-traditional-test-grids)
- [HyperExecute - Deep dive into YAML](https://www.lambdatest.com/support/docs/deep-dive-into-hyperexecute-yaml)
- [HyperExecute - CLI](https://www.lambdatest.com/support/docs/hyperexecute-cli-run-tests-on-hyperexecute-grid/)

For triggering tests on HyperExecute Grid, please download the HyperExecute CLI in accordance to your platform (i.e. Windows/macOS/Linux).

- Windows:	https://downloads.lambdatest.com/hyperexecute/windows/hyperexecute.exe
- macOS:	https://downloads.lambdatest.com/hyperexecute/darwin/hyperexecute
- Linux:	https://downloads.lambdatest.com/hyperexecute/linux/hyperexecute

Place the same in the project's root folder and provide *execution* (i.e. +x) to the executable:

<img width="747" alt="HyperExecute_Command_Copy" src="https://user-images.githubusercontent.com/1688653/206834201-533db74e-311b-48fd-a64c-1bc47f10d39c.png">

Before running the tests on LambdaTest HyperExecute Grid, you need to set the environment variables - *LT_USERNAME* and *LT_ACCESS_KEY*. Here is how you can do the same:

## Configure Environment Variables

Before the tests are run, please set the environment variables <b>LT_USERNAME</b> & <b>LT_ACCESS_KEY</b> from the terminal. The account details are available on your [LambdaTest Profile](https://accounts.lambdatest.com/detail/profile) page.

For macOS:

```bash
export LT_USERNAME=LT_USERNAME
export LT_ACCESS_KEY=LT_ACCESS_KEY
```

For Linux:

```bash
export LT_USERNAME=LT_USERNAME
export LT_ACCESS_KEY=LT_ACCESS_KEY
```

For Windows:

```bash
set LT_USERNAME=LT_USERNAME
set LT_ACCESS_KEY=LT_ACCESS_KEY
```

## Test Orchestration using Auto Split

Auto-test split (or auto split) workflow comes handy when you want to run tests at predefined concurrency and distribute the tests over the available infrastructure.

Concurrency can be on any level (e.g. file, module, test suite, test, scenario, etc). It is up to the user to decide the level at which they want to split the tests. Further information about auto-splitting is available in the [official documentation of HyperExecute on smart auto test splitting](https://www.lambdatest.com/support/docs/deep-dive-into-hyperexecute-yaml#smart-auto-test-splitting)

The YAML file located in *yaml/winappdriver_hyperexecute_autosplit.xml* fetches the *tests* from *xml/testng_win.xml*. Running the *testDiscovery* command on the terminal gives the following output:

<img width="1253" alt="AutoSplit_Command_Execution" src="https://user-images.githubusercontent.com/1688653/206831096-7b63fd68-b9ce-4ff8-9c0f-0f138ad9d64b.png">

Here is the complete YAML file that orchestrates the tests for auto split execution: 


```yaml
---
version: 0.1
globalTimeout: 150
testSuiteTimeout: 150
testSuiteStep: 150

runson: win

autosplit: true
retryOnFailure: true

maxRetries: 5
concurrency: 2

env:
  # PAT: ${{ .secrets.testKey }}
  CACHE_DIR: m2_cache_dir

cacheKey: '{{ checksum "pom.xml" }}'
cacheDirectories:
  - .m2

pre:
  # Download and install packages in the CACHE_DIR.
  # Skip execution of the tests in the pre step
  - mvn -Dmaven.repo.local=./.m2 dependency:resolve

post:
  - cat yaml/win/winappdriver_hyperexecute_autosplit.yaml

mergeArtifacts: true

uploadArtefacts:
 - name: ExecutionSnapshots
   path:
    - target/surefire-reports/html/**

testDiscovery:
  type: raw
  mode: dynamic
  command: grep 'test name' xml/testng_win.xml | awk '{print$2}' | sed 's/name=//g' | sed 's/\x3e//g'

testRunnerCommand: mvn test `-Dplatname=win `-Dmaven.repo.local=./.m2 dependency:resolve `-DselectedTests=$test
```
### Execution - Auto Split ###

Since the concurrency is set to 2, both the tests will be run independently. Shown below are some of the auto-split execution screeshots that indicate that test methods in *Notepad* and *ClassicCalculator* executed in parallel on HyperExecute grid:

<img width="1398" alt="AutoSplit_2" src="https://user-images.githubusercontent.com/1688653/206882961-8961e3c8-9d9f-4504-8f58-e0ce280dd0c5.png">
<img width="1398" alt="AutoSplit_3" src="https://user-images.githubusercontent.com/1688653/206882959-f28789c7-8b69-481c-8a89-6691a6e21bdb.png">
<img width="1398" alt="AutoSplit_4" src="https://user-images.githubusercontent.com/1688653/206882955-c8d103e0-ea32-4ed3-ac22-b89bee2d2c55.png">

Here are the screenshots from the *HyperExecute Automation Dashboard* on LambdaTest:

<img width="1413" alt="Autosplit_Dashboard_1" src="https://user-images.githubusercontent.com/1688653/206883191-f72275d8-991c-4485-a888-5a0c97910248.png">

<img width="1407" alt="Autosplit_Dashboard_2" src="https://user-images.githubusercontent.com/1688653/206883189-c034aca0-34ec-4a35-ba58-4215e67f931d.png">

<img width="1407" alt="Autosplit_Dashboard_3" src="https://user-images.githubusercontent.com/1688653/206883188-71f66c9b-4344-4fa7-8c33-1fea7dce4e0c.png">

## Project Execution

Run the following command on the terminal to trigger tests on local Grid and LambdaTest Selenium Grid

```bash
mvn test
```

Tests on local Grid and LambdaTest Selenium Grid will be executed in parallel. Here is the execution snapshot on LambdaTest Selenium Grid:

<img width="1389" alt="Screenshot_1" src="https://user-images.githubusercontent.com/1688653/185630138-a0c707b2-0359-478e-b67d-472760ef56e9.png">
<img width="1118" alt="Screenshot_2" src="https://user-images.githubusercontent.com/1688653/185630145-fed1a96d-516b-481c-9e2d-82c2a6328e32.png">

Here is the screenshot of tests run on LambdaTest Grid:

<img width="1435" alt="Screenshot_3" src="https://user-images.githubusercontent.com/1688653/185632072-d3c5298b-e607-409c-be67-3b2c0fd5e818.png">


## Need Assistance?
Discuss your queries by writing to me at [himanshu[dot]sheth[at]gmail](mailto:himanshu.sheth@gmail.com) or you can ping me on the following social media sites:

<b>Twitter</b>: [@hjsblogger](https://www.twitter.com/hjsblogger)
<br/>
<b>LinkedIn</b>: [@hjsblogger](https://linkedin.com/in/hjsblogger)
<br/>
<b>Facebook</b>: [@hjsblogger](https://facebook.com/hjsblogger)



