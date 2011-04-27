(import com.is.students.*)
(import java.util.*)
(import com.is.util.*)
(import com.is.jess.*)

(defglobal ?*x* = 0)
(load-function LoadDatabase)
(load-database)



(deffunction modifyfact ($?args) 
    
    (bind ?val-zero 0)
    (bind ?val-one 1)
    (bind ?val-two 2)
    (bind ?id (nth$ 2 $?args))
    (bind ?mode (nth$ 1 $?args))
    (bind ?length (length$ $?args))
    (bind ?s1 "student")
    (bind ?s2 "course")
    (bind ?flag 1)
  	
    (if (= (str-compare ?mode ?s1) ?val-zero) ;Se quiere modificar un student
        then ;(printout t "Student" crlf) 
      	(bind ?studentname (nth$ 3 $?args))
        (bind ?listofcourse (new HashSet))
        (if (= (call Operation find ?id) ?val-one) ;Si existe id
        	then (printout t "Existe id" crlf) 
        	(for (bind ?i 4) (<= ?i ?length) (++ ?i)
            	(if (= (call Operation isCourse (nth$ ?i $?args)) 1) 
                    then (bind ?course-aux (call Operation seek (nth$ ?i $?args)))
            		(call ?listofcourse add ?course-aux)
            	)    
       		)
		(bind ?operation (new Operation))
        (call ?operation updateStudent ?id ?studentname ?listofcourse)    
        )
        (for (bind ?i 0) (= ?flag 1) (++ ?i)
            (bind ?f (fact-id ?i))
        	(bind ?t (?f getDeftemplate))
            ;(printout t "Nombre template" (?t getName) crlf)
            (if (= (str-compare (?t getName) "MAIN::student") ?val-zero) ;nombre template = student
                then ;(printout t "Entra template" crlf)
                (bind ?valor (?f getSlotValue "studentId"))
                (if (= ?valor ?id)
                    then  (modify ?f (studentName ?studentname) (courses ?listofcourse))
                    (bind ?flag 0)
                      )
            )               	   
        )    
       
    )   
         
    (if (= (str-compare ?mode ?s2) ?val-zero) ;Se quiere modificar un course
    		then (printout t "Course" crlf) 
            (bind ?coursename (nth$ 3 $?args))
		    (if (= (call Operation find ?id) ?val-one) ;Si existe id
            	then (printout t "Existe id" crlf)
            	(bind ?op (new Operation))
        		(call ?op courseUpdate ?id ?coursename)    		 
            )
        	(for (bind ?j 0) (= ?flag 1) (++ ?j) 
            	(bind ?fact (fact-id ?j))
            	(bind ?template (?fact getDeftemplate))
            	(if (= (str-compare (?template getName) "MAIN::course") ?val-zero) ;nombre template = course
                	then (printout t "Entra template" crlf)
                	(bind ?val (?fact getSlotValue "courseId"))
                	(if (= ?val ?id)
                    	then  (modify ?fact (courseName ?coursename))
                    	(bind ?flag 0)
                    )
                )
            )
    )

)
    
    
    
    

(modifyfact course 7 Matemáticas Oníricas)
(facts)