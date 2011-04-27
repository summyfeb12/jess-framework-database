(import com.is.students.*)
(import java.util.*)
(import com.is.util.*)
(import com.is.jess.*)

(load-function LoadDatabase)
(load-database)

(deffunction deletefact ($?args) 

    (bind ?mode (nth$ 1 $?args))
    (bind ?id (nth$ 2 $?args))
    (bind ?val-zero 0)
    (bind ?val-one 1)
    (bind ?val-two 2)
    (bind ?s1 "student")
    (bind ?s2 "course")
    (bind ?flag 1)
    
    (if (= (str-compare ?mode ?s1) ?val-zero) ;Delete student
    	then (if (= (call Operation find ?id) ?val-one) ;Si existe id
    			then (bind ?op (new Operation))
        		(call ?op delete ?id ?val-zero) ; zero porque es student 
            )
    	
        (for (bind ?i 0) (= ?flag 1) (++ ?i)
            (bind ?f (fact-id ?i))
        	(bind ?t (?f getDeftemplate))
            ;(printout t "Nombre template" (?t getName) crlf)
            (if (= (str-compare (?t getName) "MAIN::student") ?val-zero) ;nombre template = student
                then ;(printout t "Entra template" crlf)
                (bind ?valor (?f getSlotValue "studentId"))
                (if (= ?valor ?id)
                    then  (retract ?i) 
                    (bind ?flag 0)
                      )
            )               	   
        )    
    )
    
    (if (= (str-compare ?mode ?s2) ?val-zero) ;Delete course
    	then (if (= (call Operation find ?id) ?val-one) ;Si existe id
    			then (bind ?op (new Operation))
        		(call ?op delete ?id ?val-one) 
            )
    	(for (bind ?j 0) (= ?flag 1) (++ ?j) 
            	(bind ?fact (fact-id ?j))
            	(bind ?template (?fact getDeftemplate))
            	(if (= (str-compare (?template getName) "MAIN::course") ?val-zero) ;nombre template = course
                	then ;(printout t "Entra template" crlf)
                	(bind ?val (?fact getSlotValue "courseId"))
                	(if (= ?val ?id)
                    	then  (retract ?j)
                    	(bind ?flag 0)
                    )
                )
            ) 
    )	
)
    
(deletefact course 7)
(facts)    