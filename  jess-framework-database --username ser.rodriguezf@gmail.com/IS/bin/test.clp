(import com.is.students.*)

(deftemplate Students (slot studentName) (slot studentSurname) (slot studentPhone))


(load-function SaveStudent)
(SaveStudent manolito)

(facts)