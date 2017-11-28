(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn deal []
  (let [shuffled (shuffle cards)
        p1 (take 26 shuffled)
        p2 (drop 26 shuffled)]
    [p1 p2]))


(defn play-round [player1-card player2-card]
  (let [[ss rks] (map (comp set vector) player1-card player2-card)
        ranks-eq? (apply = rks)]
    (if ranks-eq?
      (if (= (some ss suits) (player1-card 0))
        player2-card
        player1-card)
      (if (= (some rks ranks) (player1-card 1))
        player2-card
        player1-card))))
    

(defn play-game [player1-cards player2-cards] 
  (if (some (comp nil? seq) [player1-cards player2-cards])
    [player1-cards player2-cards]
    (let [c1 (first player1-cards)
          c2 (first player2-cards)
          r1 (rest (vec player1-cards))
          r2 (rest (vec player2-cards))
          winner (play-round c1 c2)]
      (if (= c1 winner)
        (recur (conj r1 c1 c2) r2)
        (recur r1 (conj r2 c1 c2))))))


