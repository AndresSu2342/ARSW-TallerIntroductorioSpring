# Escuela Colombiana de Ingeniería
# Arquitecturas de Software - ARSW

---

### Integrantes: Joan S. Acevedo Aguilar - Cesar A. Borray Suarez

---

### Taller – Principio de Inversión de dependencias, Contenedores Livianos e Inyección de dependencias.

Parte I. Ejercicio básico.

Para ilustrar el uso del framework Spring, y el ambiente de desarrollo para el uso del mismo a través de Maven (y NetBeans), se hará la configuración de una aplicación de análisis de textos, que hace uso de un verificador gramatical que requiere de un corrector ortográfico. A dicho verificador gramatical se le inyectará, en tiempo de ejecución, el corrector ortográfico que se requiera (por ahora, hay dos disponibles: inglés y español).

1. Abra el los fuentes del proyecto en NetBeans.

Para este caso usamos el IDE InteliJ:

![Image](https://github.com/user-attachments/assets/df5308ca-9b78-43ad-b422-343427bfc590)

2. Revise el archivo de configuración de Spring ya incluido en el proyecto (src/main/resources). El mismo indica que Spring buscará automáticamente los 'Beans' disponibles en el paquete indicado.

Este archivo de configuración de Spring usa context:component-scan para indicar que Spring debe buscar automáticamente componentes dentro del paquete `edu.eci.arsw`. Esto significa que cualquier clase anotada con `@Component`, `@Service`, `@Repository` o  `@Controller` dentro de ese paquete será detectada automáticamente y administrada como un "Bean" en el contexto de Spring, tal cual como nos indicaba el enunciado.

![Image](https://github.com/user-attachments/assets/20e81da1-d944-4448-a205-053b31adc388)

3. Haciendo uso de la [configuración de Spring basada en anotaciones](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html) marque con las anotaciones @Autowired y @Service las dependencias que deben inyectarse, y los 'beans' candidatos a ser inyectadas -respectivamente-:

* GrammarChecker será un bean, que tiene como dependencia algo de tipo 'SpellChecker'.
	


   ```sh
   @Service
   public class GrammarChecker {
	   
	   @Autowired
	   SpellChecker sc;
		   
	   .
	   .
	   .
   }
   ```

* EnglishSpellChecker y SpanishSpellChecker son los dos posibles candidatos a ser inyectados. Se debe seleccionar uno, u otro, mas NO ambos (habría conflicto de resolución de dependencias). Por ahora haga que se use EnglishSpellChecker.
 
```sh

```

5.	Haga un programa de prueba, donde se cree una instancia de GrammarChecker mediante Spring, y se haga uso de la misma:

	```java
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		GrammarChecker gc=ac.getBean(GrammarChecker.class);
		System.out.println(gc.check("la la la "));
	}
	```
	
```sh
public class Main {

    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        GrammarChecker gc = ac.getBean(GrammarChecker.class);
        System.out.println(gc.check("la la la "));
    }

}
```

6.	Modifique la configuración con anotaciones para que el Bean ‘GrammarChecker‘ ahora haga uso del  la clase SpanishSpellChecker (para que a GrammarChecker se le inyecte EnglishSpellChecker en lugar de  SpanishSpellChecker. Verifique el nuevo resultado.

Con `@Qualifier("englishSpellChecker")`: Especificamos que se inyecte EnglishSpellChecker en lugar de SpanishSpellChecker, de esta manera evitamos conflicto en Spring, dandole a entender cuál de los dos debe inyectar.

```sh
@Service
public class GrammarChecker {

	@Autowired
	@Qualifier("englishSpellChecker")
	SpellChecker sc;
	
	.
	.
	.
}
```

Y de esa manera obtenemos el siguiente resultado:

![Image](https://github.com/user-attachments/assets/5762de4b-2028-4702-9efc-9e123a56d1b0)