(ns alphabet-cipher.coder)

(defn- offset [l]
  (- (int l) (int \a)))

(defn- letter [idx]
  (char (+ 97 idx)))

(def alphabet (range (int \a) (+ (int \z) 1)))
(def alphabet-size (count alphabet))

(defn- encode-char [k c]
  (let [coff (offset c)
        koff (offset k)
        idx (+ coff koff)]
    (char (first (drop idx (cycle alphabet))))))

(defn- decode-char [k c]
  (let [koff (offset k)
        coff (+ (- alphabet-size koff) (offset c))]
    (char (first (drop coff (cycle alphabet))))))

(defn- decipher-char [c m]
  (let [moff (offset m)
        koff (- (+ alphabet-size (offset c)) moff)]
    (letter (first (drop koff (cycle (range alphabet-size)))))))

(defn- repeating-word [s]
  (let [parts (rest (reductions str "" s))
        matcher (fn [part]
                  (let [words (partition (count part) s)
                        has-words (> (count words) 1)
                        matches (apply = words)]
                    (when (and has-words matches)
                      part)))]
    (first (filter #(not (nil? %))
        (map matcher parts)))))
          

(defn encode [keyword message]
  (apply str (map encode-char (cycle keyword) message)))

(defn decode [keyword message]
  (apply str (map decode-char (cycle keyword) message)))

(defn decipher [cipher message]
  (let [keyword (apply str (map decipher-char cipher message))]
    (repeating-word keyword)))

