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

Place the same in the project's root folder and provide *execution* (i.e. +x) permission to the executable:

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

captureScreenRecordingForScenarios: true

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

Since the concurrency is set to 2, both the tests will be run independently. Shown below are some of the auto-split execution screenshots that indicate that test methods in *Notepad* and *ClassicCalculator* executed in parallel on HyperExecute grid:

<img width="1398" alt="AutoSplit_2" src="https://user-images.githubusercontent.com/1688653/206882961-8961e3c8-9d9f-4504-8f58-e0ce280dd0c5.png">
<img width="1398" alt="AutoSplit_3" src="https://user-images.githubusercontent.com/1688653/206882959-f28789c7-8b69-481c-8a89-6691a6e21bdb.png">
<img width="1398" alt="AutoSplit_4" src="https://user-images.githubusercontent.com/1688653/206882955-c8d103e0-ea32-4ed3-ac22-b89bee2d2c55.png">

Here are the screenshots from the *HyperExecute Automation Dashboard* on LambdaTest:

<img width="1413" alt="Autosplit_Dashboard_1" src="https://user-images.githubusercontent.com/1688653/206883191-f72275d8-991c-4485-a888-5a0c97910248.png">

<img width="1407" alt="Autosplit_Dashboard_2" src="https://user-images.githubusercontent.com/1688653/206883189-c034aca0-34ec-4a35-ba58-4215e67f931d.png">

<img width="1407" alt="Autosplit_Dashboard_3" src="https://user-images.githubusercontent.com/1688653/206883188-71f66c9b-4344-4fa7-8c33-1fea7dce4e0c.png">

A video gets generated for each test scenario where you can see the interactions being done on the elements in the application. Simply click on "Watch Video" to see the test execution in action:

<img width="1413" alt="AutoSplit_Watch_Video_1" src="https://user-images.githubusercontent.com/1688653/207234167-be40bf50-383c-466b-9a5e-99703a26d5e0.png">

<img width="1437" alt="AutoSplit_Watch_Video_2" src="https://user-images.githubusercontent.com/1688653/207234166-a79be61f-ee64-40c7-bc4e-85e00dcc83b3.png">

## Test Orchestration using Matrix Multiplexing

Choose the matrix-based build multiplexing for running similar test cases over a variety of different combinations. For example, an environment combination could be a browser and different versions of the corresponding browser. It is not only limited to browser & OS combinations, matrix-based multiplexing can be used for custom params like files, folders, tags, features, scenarios, input values, etc.

Consider a scenario where the matrix calculation results in 10 different tasks (or test combinations). In such a case, 10 different VMs will be parallely spawned and all the tasks (or tests) will be run in parallel on the respective VM.

Further information about auto-splitting is available in the [official documentation of HyperExecute on matrix based multiplexing](https://www.lambdatest.com/support/docs/deep-dive-into-hyperexecute-yaml#matrix-based-build-multiplexing)

The test names mentioned in *xml/testng_win.xml* are added in the form of matrix in *yaml/winappdriver_hyperexecute_matrix.xml*. The *mvn* command runs the tests in parallel (depending on the *concurrency* level set in YAML) on the HyperExecute Grid.

```yaml
matrix:
  tests: ["Notepad", "ClassicCalculator"]
........
........
........
testSuites:
  - mvn test `-Dplatname=win `-Dmaven.repo.local=m2_cache_dir `-DselectedTests=$tests
```

Here is the complete YAML file that orchestrates the tests for matrix execution: 

```yaml
---
version: 0.1
globalTimeout: 150
testSuiteTimeout: 150
testSuiteStep: 150

runson: win
retryOnFailure: true

maxRetries: 5
concurrency: 2

captureScreenRecordingForScenarios: true

env:
  # PAT: ${{ .secrets.testKey }}
  CACHE_DIR: m2_cache_dir

cacheKey: '{{ checksum "pom.xml" }}'
cacheDirectories:
  - $CACHE_DIR

matrix:
  tests: ["Notepad", "ClassicCalculator"]

# shell: bash

pre:
  # Download and install packages in the CACHE_DIR.
  # Skip execution of the tests in the pre step
  - mvn -Dmaven.repo.local=$CACHE_DIR -Dmaven.test.skip=true clean install

post:
  - cat yaml/win/winappdriver_hyperexecute_matrix.yaml

mergeArtifacts: true

uploadArtefacts:
 - name: ExecutionSnapshots
   path:
    - target/surefire-reports/html/**

testSuites:
  - mvn test `-Dplatname=win `-Dmaven.repo.local=m2_cache_dir `-DselectedTests=$tests
```

### Execution - Matrix Multiplexing ###

Since the concurrency is set to 2, both the tests will be run independently. Shown below are some of the matrix execution screeshots that indicate that test methods in *Notepad* and *ClassicCalculator* executed in parallel on HyperExecute grid:

<img width="1401" alt="Matrix_1" src="https://user-images.githubusercontent.com/1688653/206883529-c4e3e84f-7e10-4c37-9a5d-c636cffda912.png">
<img width="1401" alt="Matrix_2" src="https://user-images.githubusercontent.com/1688653/206883528-522f5ee1-9c4f-4e4d-ba30-48fd2193bccd.png">
<img width="1396" alt="Matrix_3" src="https://user-images.githubusercontent.com/1688653/206883526-69fdfdd1-fcf5-4ad4-a168-6a4694d0adab.png">

Here are the screenshots from the *HyperExecute Automation Dashboard* on LambdaTest:

<img width="1410" alt="Matrix_Dashboard_1" src="https://user-images.githubusercontent.com/1688653/206883715-68825012-2313-4c14-a455-1da03f2e7ccd.png">

<img width="1410" alt="Matrix_Dashboard_2" src="https://user-images.githubusercontent.com/1688653/206883714-71a5cb44-3cc1-4ea9-9b23-baf72482f9f0.png">

<img width="1410" alt="Matrix_Dashboard_3" src="https://user-images.githubusercontent.com/1688653/206883713-4042d18e-df62-4ef0-be57-772fcab63b21.png">

A video gets generated for each test scenario where you can see the interactions being done on the elements in the application. Simply click on "Watch Video" to see the test execution in action:

<img width="1437" alt="Matrix_Watch_Video_1" src="https://user-images.githubusercontent.com/1688653/207234164-f59a8a7f-d6e4-42d9-b0c3-74cc181bbfac.png">
<img width="1437" alt="Matrix_Watch_Video_2" src="https://user-images.githubusercontent.com/1688653/207234161-fa0a06a8-7cad-4f46-abd6-e35e72e44ef9.png">

## Need Assistance?
Feel free to fork the repo and contribute to make it better! Email to [himanshu[dot]sheth[at]gmail[dot]com](mailto:himanshu.sheth@gmail.com) for any queries or ping me on the following social media sites:

<b>Twitter</b>: [@hjsblogger](https://www.twitter.com/hjsblogger)
<br/>
<b>LinkedIn</b>: [@hjsblogger](https://linkedin.com/in/hjsblogger)
