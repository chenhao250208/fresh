# Fresh - 软件工程大作业: "网上生鲜超市"
## Hello There! Welcome To The Soure Code Page Of An Online Fresh Food Market Developed By Our Team!
### Our App will be deployed some time in the future. MAYBE IN THE FUTURE FAR FROM DOOMSDAY :)
To be Concluded, this project is coded in Java with the greatest IDE in the uinverse (IntelliJ). We've made a decision to use the SpringBoot
framework, with the help of an powerful and easy-to-use ORM named Mybatis, to build this project.
### Anyway, there is a saying goes "a good framework is half of success."
> code sample
```java
// Model layer
@Repository
public class Goods {
    private String goodsName;
    private float privce;
    private float cost;
    private String image;
    private int deposit;
    // so on...
    
    // getters and setters
    public getGoodsName(){
        return this.goodsName;
    }
    public setGoodsName(String goodsName){
        this.goodsName = goodsName;
    }
    // so on...
}

// View layer
// thyemleaf is the latest template language for building a java web project 

// Controller layer
@RestController
public class [some words...]Controller{
    @RequestMapping(value = 'url patterns', mathod = 'http method type'(, and so on...))
    public String functionName(variants){
        // code
        return JSON-like-string;
    }
    // so on...
}

// Utils
public static class [some adj...]Utils {
  // functions or constants or enumerations
}
```

> That's all, thank you! :)
