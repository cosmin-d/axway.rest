# axway.rest
Java Restful Service for Axway application



 <p> Pentru realizarea acestui API am folosit:<br> Oracle WebLogic Server pentru partea de emulare server<br>
 Oracle DataBase pentru baza de date <br>
 Eclipse Java EE IDE(Oxygen) pentru IDE<br> 
 Diverse librarii in Java(ex: Jersey)</p>
<p>adresa axway.rest redirectioneaza catre aceasta pagina (readme.html)</p>
<p>servletul incarca Jersey si detecteaza requesturile API</p>
<p>am definit mai multe pachete java :</p><br> 
<p><strong>axway.db</strong> - contine clasele care ajuta la integrarea cu baza de date si realizarea conexiunii cu aceasta, cat si o clasa care defineste functiile care vor fi apelate in functie de requesturile API</p>
<p><strong>axway.rest.crud</strong> si <strong>axway.rest.status</strong> - contin clase care vor apela metode din axway.db in functie de path-ul setat</p>
<p>Astfel:<br> <strong>axway.rest/api/v1/status</strong> - "Java API Service"<br>
<strong>axway.rest/api/v1/status/version</strong> - versiunea api-ului <br>
<strong>axway.rest/api/v1/status/database </strong>- executa un query pe baza de date pentru a se asigura functionalitatea <br>
<strong>axway.rest/api/v1/read/students </strong>- returneaza intrarile din tabelul STUDENT (in format json)<br>
<strong>axway.rest/api/v1/read/courses</strong> - returneaza intarile din tabelul CURS (in format json)<br>
<strong>axway.rest/api/v1/search/student/{nume}/{prenume} </strong>- executa o cautare in baza de date dupa NUME , PRENUME student si returneaza cursurile la care acesta participa (in format json)<br>
<strong>axway.rest/api/v1/search/course/{denumire} </strong>- executa o cautare dupa denumirea cursului si returneaza studentii care participa la cursul respectiv (in format json)<br> 
Urmatoarele functii beneficiaza de o interfata web:<br>
<strong>axway.rest/api/v2/delete/course/{id_curs}/</strong> - Interfata in delete-course.html - Listeaza cursurile si ofera posibilitatea stergerii acestora.<br>
<strong>axway.rest/api/v2/delete/student/{id_student}/</strong> - Interfata in delete-student.html - Listeaza studentii si ofera posibilitatea stergerii acestora.<br>
<strong>axway.rest/api/v2/insert/student/</strong> - Interfata in insert-student.html - Pemite introducerea datelor unui student intr-un formular si inserarea lor in baza de date.<br>
<strong>axway.rest/api/v2/insert/course/</strong> - Interfata in insert-course.html - Pemite introducerea datelor unui curs intr-un formular si inserarea lor in baza de date.<br>
<strong>axway.rest/api/v2/insert/stc/ </strong>- Interfata in insert-student-to-course.html - Asociaza un student unui curs - Se introduc ambele ID-uri (id-student si id-curs) in formular.<br>
<strong>axway.rest/api/v2/update/course/{id_curs}/</strong> - Interfata in update-course.html - Listeaza cursurile si ofera posibilitatea modificarii denumirii acestora. <br>
<strong>axway.rest/api/v2/update/student/{id_student}/</strong> - Interfata in update-student.html - Listeaza studentii si ofera posibilitatea modificarii numelui / prenumelui  acestora. <br>

</p>
<br><p><strong>axway.rest.util</strong> -contine un JSON parser </p>
<br>
<p> Baza de date contine 3 tabele : <br>
<strong>STUDENT</strong> cu campurile<br><strong> +ID</strong><br><strong>+NUME</strong><br><strong>+PRENUME</strong><br><br><strong>CURS</strong> cu campurile <br><strong>+ID</strong><br><strong>+DENUMIRE</strong><br><br><strong>STUDENT_CURS</strong> cu campurile<br><strong>+ID_STUDENT</strong><br><strong>+ID_CURS</strong><br></p>
<br>
<p> S-au aplicat constrangeri de tip cheie primara si identitate pe campurile ID ale tabelelor STUDENT si CURS </p><br>
<p>S-au aplicat constrangeri de tip foreign key pe campurile ID_CURS si ID_STUDENT din tabelul STUDENT_CURS cu referinte la campurile ID din tabelele CURS respectiv STUDENT.</p><br>

