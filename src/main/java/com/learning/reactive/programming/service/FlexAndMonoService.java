package com.learning.reactive.programming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FlexAndMonoService {


    public static Mono<String> fruitMono(){
        return Mono.just("Paine Apple").log();
    }
    public static Mono<List<String>> fruitMonoFlatMap(){
        return Mono.just("Paine Apple").flatMap(s -> Mono.just(List.of(s.split("")))).log();
    }

    public static Flux<String> fruitMonoFlatMapMany(){
        return Mono.just("Paine Apple").flatMapMany(s -> Flux.just(s.split(""))).log();
    }

    public static Flux<String> fruitFlux(){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango")).log();
    }

    public static Flux<String> fruitFluxMap(){
        return Flux.fromIterable(List.of("Apple","Orange")).map(String::toUpperCase).log();
    }

    public static Flux<String> fruitFluxFilter(int number){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango")).filter(s -> s.length() > number);
    }

    public static Flux<String> fruitFluxTransform(int number){
        Function<Flux<String>, Flux<String>> filterData= data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .transform(filterData).log();
                //.filter(s -> s.length() > number);
    }

    public static Flux<String> fruitFluxTransformDefaultIfEmpty(int number){
        Function<Flux<String>, Flux<String>> filterData= data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .transform(filterData)
                .defaultIfEmpty("Default").log();
    }

    public static Flux<String> fruitFluxTransformSwitchIfEmpty(int number){
        Function<Flux<String>, Flux<String>> filterData= data -> data.filter(s -> s.length() > number);
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Pineapple", "Jack Fruit")
                        .transform(filterData))
                .log();
    }

    public static Flux<String> fruitFluxFilterAndMap(int number){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .filter(s -> s.length() > number).map(String::toUpperCase);
    }

    public static Flux<String> fruitFluxFlatMap(){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .flatMap(s -> Flux.just(s.split(""))).log();
    }

    public static Flux<String> fruitFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .flatMap(s -> Flux.just(s.split("")).delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))).log();
    }

    public static Flux<String> fruitFluxConcatMap(){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .concatMap(s -> Flux.just(s.split("")).delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))).log();
    }

    public static Flux<String> fruitFluxConcat(){
        var fruitOne = Flux.just("Apple", "Orange");
        var fruitTwo = Flux.just("Banana", "Mango");
        return Flux.concat(fruitOne,fruitTwo);
    }

    public static Flux<String> fruitFluxConcatWith(){
        var fruitOne = Flux.just("Apple", "Orange");
        var fruitTwo = Flux.just("Banana", "Mango");
        return fruitOne.concatWith(fruitTwo);
    }

    public static Flux<String> fruitMonoConcatWith(){
        var fruitOne = Mono.just("Apple");
        var fruitTwo = Mono.just("Mango");
        return fruitOne.concatWith(fruitTwo);
    }

    public static Flux<String> fruitFluxMerge(){
        var fruitOne = Flux.just("Apple", "Orange")
                .delayElements(Duration.ofMillis(50));
        var fruitTwo = Flux.just("Banana", "Mango")
                .delayElements(Duration.ofMillis(75));
        return Flux.merge(fruitOne,fruitTwo);
    }

    public static Flux<String> fruitFluxMergeWith(){
        var fruitOne = Flux.just("Apple", "Orange")
                .delayElements(Duration.ofMillis(50));
        var fruitTwo = Flux.just("Banana", "Mango")
                .delayElements(Duration.ofMillis(75));
        return fruitOne.mergeWith(fruitTwo);
    }

    public static Flux<String> fruitFluxMergeWithSequential(){
        var fruitOne = Flux.just("Apple", "Orange")
                .delayElements(Duration.ofMillis(50));
        var fruitTwo = Flux.just("Banana", "Mango")
                .delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fruitOne, fruitTwo);
    }

    public static Flux<String> fruitFluxZip(){
        var fruitOne = Flux.just("Apple", "Orange");
        var fruitTwo = Flux.just("Banana", "Mango");
        return Flux.zip(fruitOne, fruitTwo, (one,two)->one+two).log();
    }

    public static Flux<String> fruitFluxZipWith(){
        var fruitOne = Flux.just("Apple", "Orange");
        var fruitTwo = Flux.just("Banana", "Mango");
        return fruitOne.zipWith(fruitTwo, (one,two)->one+two).log();
    }

    public static Flux<String> fruitFluxZipTriple(){
        var fruitOne = Flux.just("Apple", "Orange");
        var fruitTwo = Flux.just("Banana", "Mango");
        var fruitThree = Flux.just("Potato", "Beans");
        return Flux.zip(fruitOne,fruitTwo,fruitThree).map(objects -> objects.getT1()+ objects.getT2()+objects.getT3()).log();
    }

    public static Mono<String> fruitMonoZipWith(){
        var fruitOne = Mono.just("Apple");
        var fruitTwo = Mono.just("Banana");
        return fruitOne.zipWith(fruitTwo, (one,two)->one+two).log();
    }

    public static Flux<String> fruitFluxFilterDoOn(int number){
        return Flux.fromIterable(List.of("Apple","Orange","Banana","Mango"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> System.out.println("s => "+ s))
                .doOnSubscribe(subscribe -> System.out.println("Subscribe => "+subscribe.toString()))
                .doOnComplete(() -> System.out.println("Complete!!!"));
    }

    public static Flux<String> fruitFluxOnErrorReturn(){
        return Flux.just("Apple","Banana")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occurred")
                )).onErrorReturn("Orange");

    }

    public static Flux<String> fruitFluxOnErrorContinue(){
        return Flux.just("Apple","Orange","Banana")
                .map(s -> {
                    if(s.equalsIgnoreCase("Orange")){
                         throw new RuntimeException("Exception Occurred");
                    }else {
                        return s.toUpperCase();
                    }
                })
                .onErrorContinue((e,f)->{
                    System.out.println("E -> "+e);
                    System.out.println("F -> "+f);
                });
    }

    public static Flux<String> fruitFluxOnErrorMap(){
        return Flux.just("Apple","Orange","Banana")
                .checkpoint("Check point 1")
                .map(s -> {
                    if(s.equalsIgnoreCase("Orange")){
                        throw new RuntimeException("Exception Occurred");
                    }else {
                        return s.toUpperCase();
                    }
                })
                .checkpoint("Check point 2")
                .onErrorMap(throwable -> {
                            System.out.println("throwable -> "+throwable);
                            return new IllegalAccessException("From Error Map");
                        });
    }

    public static Flux<String> fruitFluxOnError(){
        return Flux.just("Apple","Orange","Banana")
                .map(s -> {
                    if(s.equalsIgnoreCase("Orange")){
                        throw new RuntimeException("Exception Occurred");
                    }else {
                        return s.toUpperCase();
                    }
                })
                .doOnError(throwable -> {
                    System.out.println("throwable -> "+throwable);
                });
    }

    public static void main(String[] args) {
        FlexAndMonoService.fruitFlux().subscribe(s -> System.out.println("Flux = "+s));
        FlexAndMonoService.fruitMono().subscribe(s -> System.out.println("Mono = "+s));
        FlexAndMonoService.fruitFluxFilter(5).subscribe(s -> System.out.println("filter  = "+s));
        FlexAndMonoService.fruitFluxFilterAndMap(5).subscribe(s -> System.out.println("Flux Filter And Map  = "+s));
    }
}
