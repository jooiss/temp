package com.example.manolja.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.manolja.R
import com.example.manolja.domain.Coupon
import com.example.manolja.ui.activity.CertActivity
import com.example.manolja.ui.activity.ChatActivity
import com.example.manolja.ui.adapter.QuestAdapter

class QuestFragment : Fragment() {

    private lateinit var tvMissionContent: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnChat: Button
    private lateinit var btnPhoto: Button
    private lateinit var questAdapter: QuestAdapter

    companion object {
        private const val REQUEST_CERT = 100

        fun newInstance(missionContent: String): QuestFragment {
            val fragment = QuestFragment()
            val args = Bundle()
            args.putString("mission_content", missionContent)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quest, container, false)

        tvMissionContent = view.findViewById(R.id.tvMissionContent)
        recyclerView = view.findViewById(R.id.recyclerView)
        btnChat = view.findViewById(R.id.btnChat)
        btnPhoto = view.findViewById(R.id.btnPhoto)

        // RecyclerView의 어댑터 초기화 및 비활성화 설정
        questAdapter = QuestAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = questAdapter
        questAdapter.setItemsEnabled(false) // 초기에는 클릭 불가능하도록 설정

        // Retrieve arguments and set the data
        arguments?.let {
            val missionContent = it.getString("mission_content")
            tvMissionContent.text = missionContent
        }

        // 테스트용 쿠폰 추가
        val testCoupons = listOf(
            Coupon("쿠폰 1", "우리글방 카페", "20%", "전체 금액의 20% 할인"),
            Coupon("쿠폰 2", "광복경양식", "10%", "음료수 1개 추가 증정"),
            Coupon("쿠폰 3", "책방골목 사진관", "30%", "친구와 사진찍기")
        )
        questAdapter.addCoupons(testCoupons)

        // 채팅방 버튼 클릭 시 GroupChatActivity로 이동
        btnChat.setOnClickListener {
            val intent = Intent(activity, ChatActivity::class.java)
            startActivity(intent)
        }

        // 사진 인증 버튼 클릭 시 CertActivity로 이동
        btnPhoto.setOnClickListener {
            val intent = Intent(activity, CertActivity::class.java)
            startActivityForResult(intent, REQUEST_CERT)
        }

        // 아이템 클릭 리스너 설정
        setupItemClickListener()

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CERT && resultCode == Activity.RESULT_OK) {
            val uploadSuccess = data?.getBooleanExtra("upload_success", false) ?: false
            if (uploadSuccess) {
                questAdapter.setItemsEnabled(true) // 인증 완료 후 클릭 가능하도록 설정
            }
        }
    }

    private fun setupItemClickListener() {
        questAdapter.setOnItemClickListener { couponName ->
            // 팝업 창을 띄우는 부분
            AlertDialog.Builder(requireContext())
                .setTitle("쿠폰 선택 완료")
                .setMessage("선택된 쿠폰: $couponName\n홈 화면에서 확인하실 수 있습니다.")
                .setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}