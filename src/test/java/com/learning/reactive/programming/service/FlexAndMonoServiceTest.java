package  com.learning.reactive.programming.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Hooks;
import reactor.test.StepVerifier;


class FlexAndMonoServiceTest {

    @Test
    void fruitFlux() {
        var flux = FlexAndMonoService.fruitFlux();
        StepVerifier.create(flux).expectNext("Apple","Orange","Banana","Mango").verifyComplete();
    }

    @Test
    void fruitMono() {
        var momo = FlexAndMonoService.fruitMono();
        StepVerifier.create(momo).expectNext("Paine Apple").verifyComplete();
    }

    @Test
    void fruitFluxMap() {
         var fluxMap = FlexAndMonoService.fruitFluxMap();
         StepVerifier.create(fluxMap).expectNext("APPLE","ORANGE").verifyComplete();
    }

    @Test
    void fruitFluxFilter() {
        var fluxFilter = FlexAndMonoService.fruitFluxFilter(5).log();
        StepVerifier.create(fluxFilter).expectNext("Orange","Banana").verifyComplete();
    }

    @Test
    void fruitFluxFilterAndMap() {
        var fluxFilterAndMap = FlexAndMonoService.fruitFluxFilterAndMap(5);
        StepVerifier.create(fluxFilterAndMap).expectNext("ORANGE","BANANA").verifyComplete();
    }

    @Test
    void fruitFluxFlatMap() {
        var fluxFlatMap = FlexAndMonoService.fruitFluxFlatMap();
        StepVerifier.create(fluxFlatMap).expectNextCount(22).verifyComplete();
    }

    @Test
    void fruitFluxFlatMapAsync() {
        var fluxFlatMapAsync = FlexAndMonoService.fruitFluxFlatMapAsync();
        StepVerifier.create(fluxFlatMapAsync).expectNextCount(22).verifyComplete();
    }

    @Test
    void fruitMonoFlatMap() {
        var fruitMonoFlatMap = FlexAndMonoService.fruitMonoFlatMap();
        StepVerifier.create(fruitMonoFlatMap).expectNextCount(1).verifyComplete();
    }

    @Test
    void fruitFluxConcatMap() {
        var fruitFluxConcatMap = FlexAndMonoService.fruitFluxConcatMap();
        StepVerifier.create(fruitFluxConcatMap).expectNextCount(22).verifyComplete();
    }

    @Test
    void fruitMonoFlatMapMany() {
        var fruitMonoFlatMapMany = FlexAndMonoService.fruitMonoFlatMapMany();
        StepVerifier.create(fruitMonoFlatMapMany).expectNextCount(11).verifyComplete();
    }

    @Test
    void fruitFluxTransform() {
        var fruitFluxTransform = FlexAndMonoService.fruitFluxTransform(5);
        StepVerifier.create(fruitFluxTransform).expectNext("Orange","Banana").verifyComplete();
    }

    @Test
    void fruitFluxTransformDefaultIfEmpty() {
        var fruitFluxTransformDefaultIfEmpty = FlexAndMonoService.fruitFluxTransformDefaultIfEmpty(6);
        StepVerifier.create(fruitFluxTransformDefaultIfEmpty).expectNext("Default").verifyComplete();
    }

    @Test
    void fruitFluxTransformSwitchIfEmpty() {
        var fruitFluxTransformSwitchIfEmpty = FlexAndMonoService.fruitFluxTransformSwitchIfEmpty(6);
        StepVerifier.create(fruitFluxTransformSwitchIfEmpty).expectNext("Pineapple", "Jack Fruit").verifyComplete();
    }

    @Test
    void fruitFluxConcat() {
        var fruitFluxConcat = FlexAndMonoService.fruitFluxConcat().log();
        StepVerifier.create(fruitFluxConcat).expectNext("Apple", "Orange", "Banana", "Mango").verifyComplete();
    }

    @Test
    void fruitFluxConcatWith() {
        var fruitFluxConcatWith = FlexAndMonoService.fruitFluxConcatWith().log();
        StepVerifier.create(fruitFluxConcatWith).expectNext("Apple", "Orange", "Banana", "Mango").verifyComplete();
    }

    @Test
    void fruitMonoConcatWith() {
        var fruitMonoConcatWith = FlexAndMonoService.fruitMonoConcatWith().log();
        StepVerifier.create(fruitMonoConcatWith).expectNext("Apple", "Mango").verifyComplete();
    }

    @Test
    void fruitFluxMerge() {
        var  fruitFluxMerge = FlexAndMonoService.fruitFluxMerge().log();
        StepVerifier.create(fruitFluxMerge).expectNext("Apple","Banana","Orange", "Mango").verifyComplete();
    }

    @Test
    void fruitFluxMergeWith() {
        var  fruitFluxMergeWith = FlexAndMonoService.fruitFluxMergeWith().log();
        StepVerifier.create(fruitFluxMergeWith).expectNext("Apple","Banana","Orange", "Mango").verifyComplete();
    }

    @Test
    void fruitFluxMergeWithSequential() {
        var  fruitFluxMergeWithSequential = FlexAndMonoService.fruitFluxMergeWithSequential().log();
        StepVerifier.create(fruitFluxMergeWithSequential).expectNext("Apple", "Orange", "Banana", "Mango").verifyComplete();

    }

    @Test
    void fruitFluxZip() {
        var  fruitFluxZip = FlexAndMonoService.fruitFluxZip();
        StepVerifier.create(fruitFluxZip).expectNext("AppleBanana", "OrangeMango").verifyComplete();
    }

    @Test
    void fruitFluxZipWith() {
        var  fruitFluxZipWith = FlexAndMonoService.fruitFluxZipWith();
        StepVerifier.create(fruitFluxZipWith).expectNext("AppleBanana", "OrangeMango").verifyComplete();
    }

    @Test
    void fruitFluxZipTriple() {
        var  fruitFluxZipTriple = FlexAndMonoService.fruitFluxZipTriple();
        StepVerifier.create(fruitFluxZipTriple).expectNext("AppleBananaPotato", "OrangeMangoBeans").verifyComplete();
    }

    @Test
    void fruitMonoZipWith() {
        var  fruitMonoZipWith = FlexAndMonoService.fruitMonoZipWith();
        StepVerifier.create(fruitMonoZipWith).expectNext("AppleBanana").verifyComplete();
    }

    @Test
    void fruitFluxFilterDoOn() {
        var fruitFluxFilterDoOn = FlexAndMonoService.fruitFluxFilterDoOn(5).log();
        StepVerifier.create(fruitFluxFilterDoOn).expectNext("Orange","Banana").verifyComplete();
    }

    @Test
    void fruitFluxOnErrorReturn() {
        var fruitFluxOnErrorReturn = FlexAndMonoService.fruitFluxOnErrorReturn().log();
        StepVerifier.create(fruitFluxOnErrorReturn).expectNext("Apple","Banana","Orange").verifyComplete();
    }

    @Test
    void fruitFluxOnErrorContinue() {
        var fruitFluxOnErrorContinue = FlexAndMonoService.fruitFluxOnErrorContinue().log();
        StepVerifier.create(fruitFluxOnErrorContinue).expectNext("APPLE","BANANA").verifyComplete();
    }

    @Test
    void fruitFluxOnErrorMap() {
        //Hooks.onOperatorDebug();
        var fruitFluxOnErrorMap = FlexAndMonoService.fruitFluxOnErrorMap().log();
        StepVerifier.create(fruitFluxOnErrorMap).expectNext("APPLE")
                .expectError(IllegalAccessException.class)
                .verify();
    }

    @Test
    void fruitFluxOnError() {
        var fruitFluxOnError = FlexAndMonoService.fruitFluxOnError().log();
        StepVerifier.create(fruitFluxOnError).expectNext("APPLE")
                .expectError(IllegalAccessException.class)
                .verify();
    }
}