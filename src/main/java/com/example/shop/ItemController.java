// 상품과 관련된 API는 다 여기 보관하고 싶음
// 비슷한 api는 한곳에 다 모아 놓는게 좋음
package com.example.shop; // 이 파일안에 있는 클래스를 쓰고싶을 때 경로

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@RequiredArgsConstructor //<-롬복 문법, 없으면 Autowired 쓰면 된다.
public class ItemController {
    //컨트롤러 있으면 public없어도 다른 폴더에서 이 클래스를 쓸 수 있지만,
    //보통은 public을 붙여줌
    private final ItemRepository itemRepository; //new ItemRepository() 들어있음
    private final ItemService itemService;

    // 롬복 라이브러리 없을 시.. Autowired로 new ItemRepository() 하나 뽑고, itemRepository 변수에 넣으라고 시키는 것
    // 롬복 라이브러리가 있어서 @RequiredArgsconstructor으로 생성자 만들 수 있는거고 아니면 Autowired 쓰면 된다
    // RequiredArgsconstructors는 final 변수나 null이 아닌 변수에서 생성자 만들 수 있음
    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.itemService = new ItemService();
    }

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll(); //테이블 모든 데이터 가져옴
        System.out.println(result); // println안에서는 테이블에 toString함수 있으면 자동으로 문자열로 변경되어 출력됨
        System.out.println(result.get(0).getPrice());

        var a = new Item(); // 새로운 item 객체가 만들어져서 빈객체가 만들어짐.
        System.out.println(a); // null값 출력됨.

        // itemRepository.save(??); 테이블에 (??)데이터 넣어줌
        model.addAttribute("items", result); //속성 이름, 속성 값
        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }
    //(참고) url 작명시 명사가 좋음
    @PostMapping("/add") //엔드포인트
    String add(@RequestParam String title, @RequestParam Integer price) throws Exception{
        //유저가 보낸 데이터 타입을 위쪽의 타입으로 변환하는거라 변환하고 싶은 타입으로 기입
        /* 따로 함수를 만들어서 수행
        System.out.println(title);
        System.out.println(price);
        Item item = new Item();
        item.setPrice(price);
        item.setTitle(title);
        // 행 복사는 ctrl + d
        itemRepository.save(item);
        */

        //throw new Exception(); // 이 자리에서 에러가 날것임

        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @PostMapping("/addItem")
    String addItem(@ModelAttribute Item item){
        // <input> 데이터들을 바로 object로 변환하려면 @ModelAttribute 어노테이션 쓰기
        itemRepository.save(item);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
            model.addAttribute("item", item.get()); //Optional쓰면 get()
        }
        return "detail.html"; //이거 실행해줌
    }

    //예외 처리 연습
    @GetMapping("/details/{id}")
    ResponseEntity<String> details(@PathVariable Long id, Model model) {
        //이렇게 할 경우, try catch문 안에서 나는 에러만 잡을 수 있기 때문에
        //모든 에러를 캐치해주는 스프링 문법도 있음.
        try {
            throw new Exception();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                // 에러 메시지 출력하는건 개발할 때 유용
                //return "redirect:/list";
                //return ResponseEntity.status(400).body("니잘못임");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("니잘못임");
        }
        //🍎 위에꺼(try catch), 밑에꺼 (@ExceptionHandler) 다 지우고 throw new Exception 실행해보슈
    }
    // 모든 api의 에러를 캐치하려면
    // 그래서 try catch 빼고
    // 옆에 있는 모든 api에서 exception 발생시 안의 코드를 생성해줌
    // 🍎일일이 Controller 마다 이 함수를 넣긴 귀찮으니까 MyExceptionController에 @ControllerAdvice 어노테이션 쓰자 !
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handler(){
        return ResponseEntity.status(404).body("니잘못");
    }

}
