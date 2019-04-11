---?image=images/chicago-1775878_1280.jpg
@color[white](Android Design Basics - Transition API)

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
### Animacja wejścia zawartości ekranu z góry
```kotlin
override fun onAttach(context: Context?) {
     super.onAttach(context)
     enterTransition = Slide(Gravity.TOP)
}
```

+++ 

### TransitionManager
W skład Transition Api wchodzi TransitionManager, który umożliwia przeprowadzenie tranzycji pomiędzy dwoma scenami. Sceny budowane są na podstawie ViewGroup i całego drzewa dzieci i ich parametrów. Można to robić ręcznie ale łatwiej i szybciej użyć TransitionManagera
+++
### Użycie TransitionManagera
```kotlin
button.setOnClickListener {
      TransitionManager.beginDelayedTransition(root)
      secondButton.isVisible = true
}
secondButton.setOnClickListener {
      TransitionManager.beginDelayedTransition(root)
      secondButton.isVisible = false
}
```        

+++ 
### Jak to działa?
@ul
- Transition manager tworzy scene dla podanej ViewGroup'y, w której zostają pobrane parametry widoków,
- następnie ustawia "onPreDrawListener", który zostaje powiadomiony o następnej tworzonej klatce wyświetlanego UI.
- Podczas wywołania tego callbacka wartości widoku zostają zebrane ponownie do drugiej sceny.
- Następnie zostają uruchomione tranzycje pomiędzy uzyskanymi scenami
@ulend

+++ 
```kotlin
override fun onAttach(context: Context?) {
    super.onAttach(context)
    enterTransition = TransitionInflater.from(context).inflateTransition(R.transition.second_set)
}
 ```
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<transitionSet xmlns:android="http://schemas.android.com/apk/res/android">

    <transitionSet>
        <fade android:fadingMode="fade_in"
              android:startDelay="100" />
        <slide android:slideEdge="top" />
        <targets>
            <target android:targetId="@+id/topImage" />
        </targets>
    </transitionSet>

    <fade>
        <targets>
            <target android:targetId="@+id/content" />
        </targets>
    </fade>

    <slide android:slideEdge="start">
        <targets>
            <target android:targetId="@+id/title" />
        </targets>
    </slide>
</transitionSet>
```
---
### Rodzaje tranzycji
@ul
- AutoTransition - domyślny (fade out -> changeBounds -> fade in)
- Fade, Slide, Explode
- ChngeBounds, ImageTransform, ChangeScroll
- TransitionSet
- Customowe Tranzycje
@ulend

+++
Przy użyciu TransitionSet jesteśmy w stanie osiągnąć skomplikowane animacje w bardzo opisowy i czytelny sposób
```kotlin
val transitionSet = TransitionSet()
val interpolator = BounceInterpolator()

val firstBarTransition = Slide(Gravity.TOP)
     .addTarget(R.id.topBar).setInterpolator(interpolator)
val leftBarTransition = Slide(Gravity.START)
     .addTarget(R.id.leftBar).setInterpolator(interpolator)
val bottomBarTransition = Slide(Gravity.BOTTOM)
     .addTarget(R.id.bottomBar).setInterpolator(interpolator)
val rightBarTransition = Slide(Gravity.END)
     .addTarget(R.id.rightBar).setInterpolator(interpolator)
val contentTransition = Fade().addTarget(R.id.content)

transitionSet
  .addTransition(firstBarTransition)
  .addTransition(leftBarTransition)
  .addTransition(bottomBarTransition)
  .addTransition(rightBarTransition)
  .addTransition(contentTransition)
  .setDuration(1000)
  .setOrdering(TransitionSet.ORDERING_SEQUENTIAL)

enterTransition = transitionSet
```
@[1-2](Tworzenie TransitionSet i interpolatora)
@[4-7](Ustawianie części składowych animacji)
@[14-21](Konfiguracja TransitionSet)
@[23](Ustawienie tranzycji wejścia)
+++
Tworzone tranzycje są uniwersalne i mogą być użyte zarówno jako enter/exit content transition w fragmencie / activity jak i podczas zmiany stanu na pojedyńczym widoku. Wszystkie tranzycje możliwe są do zdefiniowania zarówno w kodzie jak i w xml.

---
### Custom Transition
Od razu rzuca się w oczy mała ilość dostępnych domyślnie animacji. Na szczęście bardzo łatwo dopisać nowe.
 
```kotlin
class ScaleVisibilityTransition : Visibility {

    private var before: Float = DEFAULT_BEFORE
    private var after: Float = DEFAULT_AFTER

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val attrArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleVisibilityTransition)

        before = attrArray.getFloat(R.styleable.ScaleVisibilityTransition_before, DEFAULT_BEFORE)
        after = attrArray.getFloat(R.styleable.ScaleVisibilityTransition_after, DEFAULT_AFTER)

        attrArray.recycle()
    }

    constructor(before: Float = 0.7f, after: Float = 1.0f) : super() {
        this.before = before
        this.after = after
    }

    override fun onAppear(
        sceneRoot: ViewGroup?,
        view: View,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator {
        view.scaleX = before
        view.scaleY = before

        return ValueAnimator.ofFloat(before, after).apply {

            addUpdateListener { animation ->
                val value = animation.animatedValue
                if (value == null) {
                    return@addUpdateListener
                }

                view.scaleX = value as Float
                view.scaleY = value
            }
            interpolator = FastOutSlowInInterpolator()
        }
    }

    override fun onDisappear(
        sceneRoot: ViewGroup?,
        view: View,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator {
        view.scaleX = after
        view.scaleY = after

        return ValueAnimator.ofFloat(after, before).apply {

            addUpdateListener { animation ->
                val value = animation.animatedValue
                if (value == null) {
                    return@addUpdateListener
                }

                view.scaleX = value as Float
                view.scaleY = value
            }
            interpolator = FastOutSlowInInterpolator()
        }
    }
}
```

+++ 
```xml
<?xml version="1.0" encoding="utf-8"?>
<transitionSet xmlns:android="http://schemas.android.com/apk/res/android"
               xmlns:app="http://schemas.android.com/apk/res-auto">

    <fade />
    <transition class="pl.lightmobile.design01transitions.ScaleVisibilityTransition"
                app:before="0.5"/>
</transitionSet>
```
+++ 

Podczas tworzenia własnych tranzycji należy pamiętać, że powinny być łatwe w łączeniu i przedstawiające pojedyńczą operacje. Np. zamiast jednej tranzycji skalującej, zmieniającej alphe i kolor lepiej stworzyć je jako każda z osobna. T sama tranzycja może być użyta na wielu ekranach a także w Shared Element transition.


---
### Podsumowanie
Do zalet należy zaliczyć dobrą czytelność, możliwość przedstawienia w postaci XML, reużywalność Tranzycji i szybkość tworzenia prostych przejść.
Do prostych zastosowań wystarczy powierzchowna znajomość beginDelayedTransition.

+++
Z wad warto wymienić konieczność zrozumienia mechanizmu odpowiadającego za orkiestracje tranzycji w celu wyłapania potencjalnych błędów. 
Transition API ma problemy przy poprawnym dyrygowaniu animacji z kilku uruchomionych tranzycji na kilku zagnieżdzeniach widoków (uruchomienie przeciwstawnych animacji zadziała jednak tylko w zakresie jednego roota)

---
### Questions?

<br>

@fa[twitter gp-contact](@wojciech_warwas)

@fa[github gp-contact](@obiwanzenobi)
