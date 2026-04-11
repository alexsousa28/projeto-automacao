package com.example.demo.web.login_orange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;


public class LoginOrangePage {

    @Getter
    private static By INPUT_USERNAME = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");

    @Getter
    private static By INPUT_PASSWORD = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");

    @Getter
    private static By BTN_LOGIN = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");

    @Getter
    private static By VALIDAR_LOGIN = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6");
}
