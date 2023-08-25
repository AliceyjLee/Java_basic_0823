package com.example.demo.controller;

import com.example.demo.entity.TextEntity;
import com.example.demo.repository.TextRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/text")
public class TextController {

    // DB 의존성 주입 - 생성자를 만드는 방식으로
    private TextRepository textRepository;

    public TextController(TextRepository textRepository){
        this.textRepository = textRepository;
    }

    // DB Create Read Update Delete
    // DB Read 전제 조회
    // 리스트 형식에 for문으로 전체 데이터를 읽어와서 출력
    @GetMapping("/selectAll")
    public String GetTexts() {

        List<TextEntity> textEntityList = textRepository.findAll();

        for (TextEntity textEntity:textEntityList) {
            System.out.println(textEntity.getText());
        }

        return "selectAll";
    }

    // DB Read 한건 조회
    // 매개변수 입력받으면 해당 매개변수와 일치하면 출력
    // OPtional Null 방지?
    @GetMapping("/select")
    public String GetText(int id) {

        Optional<TextEntity> result = textRepository.findById(id);

        if(result.isPresent()) {
            TextEntity textEntity = result.get();
            System.out.println(textEntity.getText());
        }

        return "select";
    }

    // DB Insert
    // TextEntity.builder()로 객체 생성
    // textRepository.save(textEntity)에 다음 데이터를 저장 
    @GetMapping("/insert")
    public String insertText() {
        TextEntity textEntity = TextEntity.builder()
                .text("texttest")
                .build();

        textRepository.save(textEntity);
        return "insert";
    }

    // DB Update
    // 매개변수에 해당하는 데이터가 있으면 
    // result.get() 로 존재하는 데이터를 가져와서 
    // textEntity.setText("textchange")로 변경
    // textRepository.save(textEntity) 변경된 데이터를 저장
    @GetMapping("/update")
    public String updateText(int id) {
        Optional<TextEntity> result = textRepository.findById(id);

        if(result.isPresent()) {
            TextEntity textEntity = result.get();
            textEntity.setText("textchange");
            textRepository.save(textEntity);
        }

        return "update";
    }

    // DB Delete
    // 매개변수에 해당하는 데이터를 삭제
    @GetMapping("/delete")
    public String deleteText(int id) {
        textRepository.deleteById(id);

        return "delete";
    }

}
