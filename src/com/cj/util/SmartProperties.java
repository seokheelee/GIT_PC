package com.cj.util; 


import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStream; 


public class SmartProperties extends java.util.Properties { 
/** 프라퍼티 저장공간 */ 
private static SmartProperties instance = null; 

/** Properties file 이름. 자신이 사용할 프라퍼티 이름 지정 */ 
private static final String PROPERTIES_NAME = "smart.properties"; 

/** 사용자의 객체 생성 불가 
* @throws IOException */ 
private SmartProperties() throws IOException { 
super(); 

InputStream in = null; 

try { 
// java -D 옵션으로 시스템 프라퍼티에 smart.properties 를 강제 
// 설정하였을 경우 그 파일을 읽는다. 
String propPath = System.getProperty(PROPERTIES_NAME); 

if (propPath != null) { 
System.out.println("smart.properties의 경로 : " + propPath); 
in = new FileInputStream(new File(propPath)); 
} else { 
// 여기는 시스템 프라퍼티에 값이 설정되어 있지 않을 경우 
// CLASSPATH에 지정된 디렉토리에서 값을 읽는다. 
in = SmartProperties.class.getClassLoader() 
.getResourceAsStream(PROPERTIES_NAME); 
} 

this.load(in); 
} finally { 
try { 
if (in != null) { 
in.close(); 
} 
} catch (IOException e) { 
//ignored 
} 
} 
} 

/** SmartProperties의 객체를 얻는다. */ 
public static synchronized SmartProperties getInstance() { 

if (instance == null) { 
try { 
instance = new SmartProperties(); 
} catch (Exception ex) { 
System.out.println("SmartProperties 객체생성 실패."); 
ex.printStackTrace(); 
} 
} 

return instance; 
} 

/** 테스트 
* @throws */ 
public static void main(String [] args) { 
SmartProperties sp = SmartProperties.getInstance(); 

sp.list(System.out); 

System.out.println("Key a : " + sp.getProperty("ID")); 

} 
} 



/** 
이 프로그램은 CLASSPATH로 지정된 디렉토리에 있는 smart.properties라는 파일을 읽거나 Java 시작시 -D로 smart.properties라는 시스템 프라퍼티를 지정해서 지정된 파일을 읽어들여 설정값을 구축한다. 
시스템 프라퍼티 설정은 다음과 같다. 

# Java 단독 실행시 
$ java -Dsmart.properties=/home/kwon37xi/smart.properties 

# Tomcat의 경우 
$ export CATALINA_OPTS=-Dsmart.properties=/home/kwon37xi/smart.properties 


위에서 보다싶이 SmartInstance.getInstance()를 이용해서 프라퍼티 객체를 얻어야 한다. 프라퍼티 객체 구축작업을 단 한번만 하게 만들기 위해서 이다. 

PROPERTIES_NAME 상수를 바꾸어서 자신이 원하는 프라퍼티 파일 이름을 사용하면 된다. 

*/