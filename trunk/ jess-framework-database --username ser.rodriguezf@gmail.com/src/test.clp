(import com.is.students.*)

(deftemplate students (slot student-name))

(load-function SaveStudent)
(save-student juancito)
(assert (students (student-name juan)))

(facts)