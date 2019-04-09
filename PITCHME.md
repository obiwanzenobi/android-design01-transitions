### Android Design Basics - Transition API

--- 

### Dostępne mechanizmy animacji
@ul
- View animations (android.view.animation)
- Animator API (android.animation)
- Transition API
- Animated Vector Drawable 
- MotionLayout (nie omawiane tutaj)
@ulend

+++
### android.view.animation

@ul
- Najstarsze api do animacji (Od Android 1.0)
- Pozwala jedynie animować podstawowe Propercje widoku
- Wykonuje się w etapie onDraw widoku (już po measure i layout)
- Nadal wykorzystywane do window i fragment animation
- Mniej wydajne od Animator'ów (z powodu wykonania w fazie draw - brak możliwości odroczenia renderowania itd.)

@ulend

+++
### android.animation - Animator API

@ul
- Łatwiejsze w obsłudze
- Zalecane api dla mocno customowych animacji
- Dostępne od Androida 3.0 (ale sensowne od 4.0)
- W skład wchodzą ObjectAnimator, AnimatorSet, ViewPropertyAnimator, ValueAnimator
- ViePropertyAnimator pozwala na pojedyńcze invalidacje w przypadku animacji kilku propercji
- Część animatorów zaimplementowana natywnie (RenderThread)
- Dużo łatwiejsze łączenie animacji
@ulend

+++
### Animated vector drawable

@ul
- Od Android 7.1 (api 25) animowane na RenderThread
- Animator + Vector Drawable
- Dużo lepsza wydajnośc lecz mniej opcji niż w Lottie
- Dostępne funkcje: start, pause, stop
@ulend

+++
### MotionLayout
@ul
- Ciągle w alphie
- Wysokopoziomowe animacje
- Możliwość podpięcia animacji pod interakcje użytkownika (swipe, drag itd.)
@ulend

---
### Transition API - Tranzycje i ich menadżer

+++
### Czym są tranzycje?
Tranzycje skupiają sie na prostym, opisowym deklarowaniu animacji skupionych na widokach. 
Tranzycja ma za zadanie pobrać wartości początkowe i końcowe widoku (wartość pierwotna i wyjściowa) i zwrócić odpowiedni Animator, który zostanie wykorzystany do animacji widoku.

+++


###
