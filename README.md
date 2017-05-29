RxBoot - SpringBoot with RxJava Examples
===
This project demonstrates using [RxJava](https://github.com/ReactiveX/RxJava) with SpringBoot. The goal is to create a SpringBoot application that uses nothing by reactive code from the entry controllers down.

## Building
```bash
./gradlew build
```

## Running
```bash
./gradlew bootRun
```

## Endpoints

### Message (Hello World) using Observable
A basic hello world endpoint.
```bash
curl -X GET http://127.0.0.1:8080/observable

200 OK

{
    "message": "Hello World from Observable!"
}
```

### Message (Hello World) using Flowable
A basic hello world endpoint.
```bash
curl -X GET http://127.0.0.1:8080/flowable

200 OK

{
    "message": "Hello World from Flowable!"
}
```

### Message (Hello World) using Single
A basic hello world endpoint.
```bash
curl -X GET http://127.0.0.1:8080/single

200 OK

{
    "message": "Hello World from Single!"
}
```

## Spring Bits
[Spring Configuration](src/main/java/io/expanse/rxboot/config) contains the Spring configuration bits that allow the 
controllers to return raw `io.reactivex.Observable`, `io.reactivex.Flowable`, or `io.reactivex.Single` types. Ideally these classes would be incorporated into an external JAR that 
each Spring application could use. Thereby reducing the code to simply RxJava only.

## License
Copyright 2016 Ryan Scott

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
