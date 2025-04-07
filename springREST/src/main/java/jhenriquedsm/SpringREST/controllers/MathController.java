package jhenriquedsm.SpringREST.controllers;

import jhenriquedsm.SpringREST.converters.NumberConverter;
import jhenriquedsm.SpringREST.exceptions.UnsupportedMathOperationException;
import jhenriquedsm.SpringREST.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();

    private SimpleMath math = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable (value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.sum((NumberConverter.convertToDouble(numberOne)), (NumberConverter.convertToDouble(numberTwo)));
    }

    @GetMapping(value = "/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.subtraction((NumberConverter.convertToDouble(numberOne)), (NumberConverter.convertToDouble(numberTwo)));
    }

    @GetMapping(value = "/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.multiplication((NumberConverter.convertToDouble(numberOne)), (NumberConverter.convertToDouble(numberTwo)));
    }

    @GetMapping(value = "/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        if (NumberConverter.convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Cannot divide by 0");
        }
        return math.division((NumberConverter.convertToDouble(numberOne)), (NumberConverter.convertToDouble(numberTwo)));
    }

    @GetMapping(value = "/average/{numberOne}/{numberTwo}")
    public Double average(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.average((NumberConverter.convertToDouble(numberOne)), (NumberConverter.convertToDouble(numberTwo)));
    }

    @GetMapping(value = "squareRoot/{number}")
    public Double squareRoot(@PathVariable(value = "number") String number) {
        if (!NumberConverter.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.squareRoot((NumberConverter.convertToDouble(number)));
    }
}