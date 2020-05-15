# Triangle Path

Finding minimal triangle path for given triangle. For example, given following triangle:

```
    7
   6 3
  3 8 5
11 2 10 9
```

minimal path is 7 -> 6 -> 3 -> 2.

## Building the project

Project is build with `sbt` build tool. Use following command to build executable jar:

```
sbt assembly
```

It will run all tests, build a "fat" jar with all dependencies and place it in `target/scala-2.13/` directory.

## Running the project

Once the project is built use Scala interpreter to run it (triangle is read from stdio):

```
$ cat << EOF | scala target/scala-2.13/triangle-path-assembly-1.0.jar
> 7
> 6 3
> 3 8 5
> 11 2 10 9
> EOF
```

If you want to skip assembly step (building jar file) you can run it directly from sbt:

```
$ cat << EOF | sbt run
> 7
> 6 3
> 3 8 5
> 11 2 10 9
> EOF
```

Please note that it will take more time as sbt has to compile and test whole project before it can run the app.
