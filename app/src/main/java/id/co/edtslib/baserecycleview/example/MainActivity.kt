package id.co.edtslib.baserecycleview.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.edtslib.baserecyclerview.BaseRecyclerViewAdapterDelegate
import id.co.edtslib.baserecyclerview.BaseViewHolder
import id.co.edtslib.baserecycleview.example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = TestAdapter()
        adapter.list = mutableListOf("Abah", "Kaka", "Ena", "Ade")
        adapter.delegate = object : BaseRecyclerViewAdapterDelegate<String> {

            override fun onClick(t: String, position: Int, holder: BaseViewHolder<String>?) {
                Toast.makeText(this@MainActivity, "Hai $t", Toast.LENGTH_SHORT).show()
            }

            override fun onDraw(t: String, position: Int) {
                // tracker send
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.scrollToPosition(adapter.getInitialPosition())

    }
}