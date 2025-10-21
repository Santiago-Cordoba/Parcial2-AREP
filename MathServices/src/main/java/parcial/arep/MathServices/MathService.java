package parcial.arep.MathServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MathService {

    @GetMapping("/factors")
    public Map<String, Object> factors(@RequestParam Integer value){
        Map<String,Object> response = new LinkedHashMap<>();
        List<Integer> intList = new ArrayList<Integer>();
        findFactors(value, intList);
        response.put("Operation", "factors");
        response.put("Input", value);
        response.put("Output", intList);

        return response;
    }

    @GetMapping("/primes")
    public Map<String, Object> primes(@RequestParam Integer value){
        Map<String,Object> response = new LinkedHashMap<>();
        List<Integer> intList = new ArrayList<Integer>();
        for(int i = 2; i <= value; i++){
            if(isFactor(i)){
                intList.add(i);
            }
        }
        response.put("Operation", "primes");
        response.put("Input", "value");
        response.put("Output", intList);
        return response;
    }

    private void findFactors(Integer value, List<Integer> intList){
        for(int i = 1; i <= value; i++){
            if(value%i==0){
                intList.add(i);
            }
        }
    }

    private boolean isFactor(Integer value){
        List<Integer>listFacts = new ArrayList<>();
        for(int i = 1; i <= value; i++){
            if(value%i==0){
                listFacts.add(i);
            }
        }
        return listFacts.size() == 2;

    }
}
