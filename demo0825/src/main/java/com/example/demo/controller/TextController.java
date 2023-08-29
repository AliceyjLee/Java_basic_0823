package com.example.demo.controller;

import com.example.demo.entity.TextEntity;
import com.example.demo.repository.TextRepository;
import com.example.demo.service.TextService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Text;

import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/text")
public class TextController {

    // DB 의존성 주입 - 생성자를 만드는 방식으로
    private TextRepository textRepository;
    // Service 의존성 주입
    private TextService textService;

    public TextController(TextRepository textRepository, TextService textService){
        // DB 의존성 주입 - 생성자를 만드는 방식으로
        this.textRepository = textRepository;
        // Service 의존성 주입
        this.textService = textService;
    }

    // DB Create Read Update Delete
    // DB Read 전제 조회
    // 리스트 형식에 for문으로 전체 데이터를 읽어와서 출력
    @GetMapping("/selectAll")
    public String GetTexts(Model model) {

        // 기존 방식
        //List<TextEntity> textEntityList = textRepository.findAll();

        // Service 적용 방식
        List<TextEntity> textEntityList = textService.List();

        for (TextEntity textEntity:textEntityList) {
            System.out.println(textEntity.getText());
        }

        // Thymeleaf
        model.addAttribute("text", textEntityList);

        return "selectAll";
    }

    // DB Read 한건 조회
    // 매개변수 입력받으면 해당 매개변수와 일치하면 출력
    // OPtional Null 방지?
    @GetMapping("/select")
    public String GetText(int id, Model model) {

        // 기존 방식
       /* Optional<TextEntity> result = textRepository.findById(id);

        if(result.isPresent()) {
            TextEntity textEntity = result.get();
            System.out.println(textEntity.getText());
        }
        return "select";*/

        // Service 계층 적용
        TextEntity textEntity = textService.get(id);
        System.out.println(textEntity.getText());

        // Timeleaf
        model.addAttribute("text", textEntity);

        return "select";
    }

    // DB Insert
    // TextEntity.builder()로 객체 생성
    // textRepository.save(textEntity)에 다음 데이터를 저장 
    @GetMapping("/insert")
    public String insertText() {
        // 기존 방식
        /*TextEntity textEntity = TextEntity.builder()
                .text("texttest")
                .build();

        textRepository.save(textEntity);
        return "insert";*/

        // Service 계층 적용
        textService.write();

        return "insert";
    }

    // DB Update
    // 매개변수에 해당하는 데이터가 있으면 
    // result.get() 로 존재하는 데이터를 가져와서 
    // textEntity.setText("textchange")로 변경
    // textRepository.save(textEntity) 변경된 데이터를 저장
    @GetMapping("/update")
    public String updateText(int id) {
        // 기존 방식
        /*Optional<TextEntity> result = textRepository.findById(id);

        if(result.isPresent()) {
            TextEntity textEntity = result.get();
            textEntity.setText("textchange");
            textRepository.save(textEntity);
        }

        return "update";*/

        // Service 계층 적용
        textService.edit(id);

        return "update";
    }

    // DB Delete
    // 매개변수에 해당하는 데이터를 삭제
    @GetMapping("/delete")
    public String deleteText(int id) {
        // 기존 방식
        /*textRepository.deleteById(id);

        return "delete";*/
        // Service 계층 적용
        textService.delete(id);

        return "delete";
    }

}
