(import com.is.students.*)
(import com.is.jess.*)

;(deftemplate students (slot student-name))
;(load-function DatabaseConnect)
;(database-connect "/localhost/DB" "DB" "MySQL" "root" "")
;(load-function SaveStudent)
;(save-student juancito)
;(assert (students (student-name juan)))

;(facts)

(load-function LoadDatabase)
(load-database)
;(assert (student (studentId 123)(courses "Computer Science")(studentName "juan")))
;(assert (course (courseName matematicas)))
(load-function SetFact)
;(setfact course "Electricitate si Magnetism")

(load-function ModifyFact)
(load-function DeleteFact)
;(deletefact "course" 90)
(modifyfact student 5  "Marco" "Mates" "S")
;(setfact student "Juan Macho" "Maths" "ComputerScience")
(facts)
;(show-deftemplates)
;(ppdeftemplate students)