# axway.rest
Java Restful Service for Axway application


<p> Pentru realizarea acestui API am folosit:<br> Oracle WebLogic Server pentru partea de emulare server<br>
 Oracle DataBase pentru baza de date <br>
 Eclipse Java EE IDE(Oxygen) pentru IDE<br> 
 Diverse librarii in Java(ex: Jersey)</p>
<p>adresa axway.rest redirectioneaza catre aceasta pagina (readme.html)</p>
<p>servletul incarca Jersey si detecteaza requesturile API</p>
<p>am definit mai multe pachete java :</p><br> 
<p>axway.db - contine clasele care ajuta la integrarea cu baza de date si realizarea conexiunii cu aceasta, cat si o clasa care defineste functiile care vor fi apelate in functie de requesturile API</p>
<p>axway.rest.crud si axway.rest.status - contin clase care vor apela metode din axway.db in functie de path-ul setat</p>
<p>Astfel:<br> axway.rest/api/v1/status - "Java API Service"<br>
axway.rest/api/v1/status/version - versiunea api-ului <br>
axway.rest/api/v1/status/database - executa un query pe baza de date pentru a se asigura functionalitatea <br>
axway.rest/api/v1/read/students - returneaza intrarile din tabelul STUDENT (in format json)<br>
axway.rest/api/v1/read/courses - returneaza intarile din tabelul CURS (in format json)<br>
axway.rest/api/v2/search/student/{nume}/{prenume} - executa o cautare in baza de date dupa NUME , PRENUME student si returneaza cursurile la care acesta participa (in format json)<br>
axway.rest/api/v2/search/course/{denumire} - executa o cautare dupa denumirea cursului si returneaza studentii care participa la cursul respectiv (in format json)<br> </p>
<br><p>axway.rest.util -contine un JSON parser </p>

<p> axway.rest/post.html - formular de insert in baza de date - foloseste scripturi </p>
<br>
<p> Baza de date contine 3 tabele : <br>
STUDENT cu campurile<br> +ID<br>+NUME<br>+PRENUME<br><br>CURS cu campurile <br>+ID<br>+DENUMIRE<br><br>STUDENT_CURS cu campurile<br>+ID_STUDENT<br>+ID_CURS<br></p>
<br>
<p> S-au aplicat constrangeri de tip cheie primara si identitate pe campurile ID ale tabelelor STUDENT si CURS </p><br>
<p>S-au aplicat constrangeri de tip foreign key pe campurile ID_CURS si ID_STUDENT din tabelul STUDENT_CURS cu referinte la campurile ID din tabelele CURS respectiv STUDENT.</p><br>
