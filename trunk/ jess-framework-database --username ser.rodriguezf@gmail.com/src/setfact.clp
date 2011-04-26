(import com.is.students.*)
(import java.util.*)
(import com.is.util.*)


(deffunction setfact ($?args) 
    
    (bind ?val-zero 0)
    (bind ?val-one 1)
    (bind ?val-two 2)
    (bind ?length (length$ $?args))
    
    
    (if (>= ?length ?val-two) ;Caso en el que está introduciendo un Student
    
    	then (printout t "Mayor que 1 " crlf)
    	;(bind ?student (new Student))
        (bind ?student (nth$ 1 $?args))
        (bind ?listofcourse (new HashSet))
        (for (bind ?i 2) (<= ?i ?length) (++ ?i)
            ;(bind ?course (new Course))
            (if (= (bind ?flag (call Operation isCourse (nth$ ?i $?args))) 1) then 
            	(bind ?course-aux (call Operation seek (nth$ ?i $?args)))
            	;(call ?course setCourseName (nth$ ?i $?args))
            	(call ?listofcourse add ?course-aux)
                ;(bind flag 1)
            )
        )    
        ;(call ?student setCourses ?set)
        (if (> (bind ?size (call ?listofcourse size)) 0) then 
        	(bind ?studentobject (new Student ?student ?listofcourse))
        	(bind ?operation (new Operation ?studentobject))
        	(call ?operation save)
            (bind ?id (call ?operation getId))
            (call ?studentobject setStudentId ?id)
            (add ?studentobject)
        else 
            (printout t "Not available course." crlf)
        )
    )
        
        
    (if (= ?length ?val-one) ;Caso en el que introducen un Course 
       
        then (bind ?courseName (nth$ 1 $?args))
        (if (= (call Operation isCourse ?courseName) 0) ;Si no existe el curso 
            then (bind ?courseobject (new Course))
            (call ?courseobject setCourseName ?courseName)
            (bind ?operation-course (new Operation ?courseobject))
            (call ?operation-course save)
            (bind ?id-course (call ?operation-course getId))
            (call ?courseobject setCourseId ?id-course)
            (add ?courseobject)
        else
            (printout t "Already existing course." crlf)    
            
         )      
    )
            
       
    (if (< ?length ?val-one) ;Error
            then (printout t "Error. Igual a 0" crlf)

            
    )
   
)


(setfact "Juanillo" "Ciencias" "Maths")
(facts)

