package robots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Contiene una serie di regole per decidere 
 * se è allowed oppure no
 * */
public class RulesEngine {

	private List rules;
	
	public RulesEngine() {
		this.rules = new ArrayList();
	}
	
	//inserisce nella lista la parte di stringa da far passare, path è la stringa dopo allow. 
	public void allowPath(String path, boolean wildcardsAllowed ){
		add(new AllowedRule(path, wildcardsAllowed) );
	}
	
	//inserisce nella lista la parte di stringa da bloccare, path è la stringa dopo disallow.
	public void disallowPath(String path, boolean wildcardsAllowed){
		add(new DisallowedRule(path, wildcardsAllowed));
	}
	
	//aggiunge nell'arraylist
	public void add(Rule rule){
		this.rules.add(rule);
	}
	
	/**
	 * eseguire le seguenti regole in serie
	 * quando una regola ritorna un booleano , ritornare questo valore
	 * se sono state eseguite tutte le regole ritornare null, per indicare che non ci sono regole per questo percorso
	 * */
	
	public Boolean isAllowed(String path){

/**
 * Controllare tutte le regole del robots.txt
 * vale l'ultima regola trovata per lo user-agent usato
 * 
 * */		
		
		
		Iterator iterator = this.rules.iterator();
		
		Boolean val = null;
		
		while(iterator.hasNext()){
			
			Rule rule = (Rule)iterator.next();

			Boolean test = rule.isAllowed(path);
						
			
			if(test != null){
				val = test;


			}
			
		}

		return val;
	}
	
	public boolean isEmpty(){
		return this.rules.isEmpty();
	}
	
	@Override
	public String toString(){
		return "RulesEngine: " + this.rules;
	}
	
	
}
