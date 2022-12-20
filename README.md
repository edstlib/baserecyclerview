# BaseRecyclerView

## Setup
### Gradle

Add this to your project level `build.gradle`:
```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
Add this to your app `build.gradle`:
```groovy
android {
    buildFeatures {
        viewBinding true
    }
}
dependencies {
    implementation 'com.github.edstlib:baserecyclerview:latest'
}
```

### Usage

- Create a view holder by extends BaseViewHolder. Here's is example code
```kotlin
    class TestHolder(private val binding: AdapterTestBinding): BaseViewHolder<String>(binding) {
        override fun setData(
            list: MutableList<String>,
            position: Int,
            delegate: BaseRecyclerViewAdapterDelegate<String>?
        ) {
            binding.textView.text = list[position]
        }
    }
```
- Add your adapter to recycler view. Here's is example code

```kotlin
    val adapter = TestAdapter()
    adapter.list = mutableListOf("Abah", "Kaka", "Ena", "Ade")
    
    binding.recyclerView.layoutManager = LinearLayoutManager(this)
    binding.recyclerView.adapter = adapter
```

### Listening for holder actions on the BaseRecyclerView

You can set a listener to be notified when the user click or draw a holder. An example is shown below.

```kotlin
        adapter.delegate = object : BaseRecyclerViewAdapterDelegate<String> {
            override fun onClick(t: String, position: Int, holder: BaseViewHolder<String>?) {
                Toast.makeText(this@MainActivity, "Hai $t", Toast.LENGTH_SHORT).show()
            }
            
            override fun onDraw(t: String, position: Int) {
                // tracker send
            }
        }
```

### Circular Recycler View

Please override isCircular function on your adapter class

```kotlin
    override fun isCircular() = true
```

and don't forget to scroll position your recycler first after set initial data

```kotlin
    binding.recyclerView.scrollToPosition(adapter.getInitialPosition())
```