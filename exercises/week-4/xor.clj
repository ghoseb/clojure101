(ns xor 
  (:use clojure.test))

(defmacro xor
  "Evaluates exprs one at a time, from left to right.  If only one form returns
  a logical true value (neither nil nor false), returns true.  If more than one
  value returns logical true or no value returns logical true, retuns a logical
  false value.  As soon as two logically true forms are encountered, no
  remaining expression is evaluated.  (xor) returns nil."
  ; write your macro replacing the next line
  [] 1)

(defn- doit
  "Returns val and creates the side effect of printing num."
  [num val]
  (print num)
  val)

(deftest nullary
  (is (not (xor))))

(comment
(deftest unary
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 true)))
    (is (= "1" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil))))
    (is (= "1" (.toString *out*)))))
)

(comment
(deftest binary
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil) (doit 2 nil))))
    (is (= "12" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 true) (doit 2 true))))
    (is (= "12" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 nil) (doit 2 true)))
    (is (= "12" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 true) (doit 2 nil)))
    (is (= "12" (.toString *out*)))))
)

(comment
(deftest ternary
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil) (doit 2 nil) (doit 3 nil))))
    (is (= "123" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 nil) (doit 2 nil) (doit 3 true)))
    (is (= "123" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 nil) (doit 2 true) (doit 3 nil)))
    (is (= "123" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil) (doit 2 true) (doit 3 true))))
    (is (= "123" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 true) (doit 2 nil) (doit 3 nil)))
    (is (= "123" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 true) (doit 2 nil) (doit 3 true))))
    (is (= "123" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 true) (doit 2 true) (doit 3 nil))))
    (is (= "12" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 true) (doit 2 true) (doit 3 true))))
    (is (= "12" (.toString *out*)))))

(deftest n-ary
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil) (doit 2 nil) (doit 3 nil)
                  (doit 4 nil) (doit 5 nil) (doit 6 nil))))
    (is (= "123456" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil) (doit 2 true) (doit 3 nil)
                  (doit 4 true) (doit 5 nil) (doit 6 nil))))
    (is (= "1234" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (xor (doit 1 true) (doit 2 nil) (doit 3 nil)
             (doit 4 nil) (doit 5 nil) (doit 6 nil)))
    (is (= "123456" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 true) (doit 2 true) (doit 3 true)
                  (doit 4 true) (doit 5 true) (doit 6 true))))
    (is (= "12" (.toString *out*))))
  (binding [*out* (java.io.StringWriter.)]
    (is (not (xor (doit 1 nil) (doit 2 nil) (doit 3 nil)
                  (doit 4 nil) (doit 5 true) (doit 6 true))))
    (is (= "123456" (.toString *out*)))))
)

(comment (clojure.test/run-tests 'xor))
