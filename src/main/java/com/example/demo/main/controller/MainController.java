package com.example.demo.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller		// Controller ������̼��� ���� �������� �� java ������ Controller ������ �����Ѵ�.
public class MainController {
	@RequestMapping(value="/")		// ��Ʈ�� �� URL�� �������ش�. URL�� path �κ��� �ۼ����ָ� �ȴ�.
	public String main() {
		return "main";		// View�� ����, ���� �̸��� ���ڿ��� �������ش�.
	}
}