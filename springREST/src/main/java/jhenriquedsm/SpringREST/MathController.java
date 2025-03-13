package jhenriquedsm.SpringREST;

import jhenriquedsm.SpringREST.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable (value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @GetMapping(value = "/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @GetMapping(value = "/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    @GetMapping(value = "/division/{numberOne}/{numberTwo}")
    public Double division(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        if (convertToDouble(numberTwo) == 0) {
            throw new UnsupportedMathOperationException("Cannot divide by 0");
        }
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @GetMapping(value = "/average/{numberOne}/{numberTwo}")
    public Double average(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
    }

    @GetMapping(value = "squareRoot/{number}")
    public int squareRoot(@PathVariable(value = "number") String number) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return (int) Math.sqrt(convertToInt(number));
    }

    private Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Double.parseDouble(number);
        }
        return 0D;
    }

    private int convertToInt(String strNumber) {
        if (strNumber == null) return 0;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) {
            return Integer.parseInt(number);
        }
        return 0;
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}