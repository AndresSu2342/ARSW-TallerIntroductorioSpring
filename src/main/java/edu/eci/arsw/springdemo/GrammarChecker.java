package edu.eci.arsw.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GrammarChecker {

	@Autowired
	@Qualifier("englishSpellChecker")
	SpellChecker sc;

	String x;
        
        
	public SpellChecker getSpellChecker() {
		return sc;
	}

	public void setSpellChecker(SpellChecker sc) {
		this.sc = sc;
	}


	public String check(String text){
		
		StringBuffer sb=new StringBuffer();
		sb.append("Spell checking output:\n"+sc.checkSpell(text)+ "\n");
		sb.append("_ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _ . _\n");
		sb.append("Plagiarism checking output: Not available yet ");
		
		
		return sb.toString();
		
	}
	
	
}
