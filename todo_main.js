const taskList = document.querySelector(".task-list"); 
const addButton = document.querySelector(".add-btn"); 
const inputField = document.querySelector(".bottom-input"); 
const apiUrl = "http://54.180.87.12:8080/todo"; // 서버 API 주소 

// 할 일 추가
addButton.addEventListener("click", function () {
  const taskText = inputField.value; 
  if (taskText === "") {
    alert("할 일을 입력하세요!"); // 입력없없으면 경고창 띄우기
    return;
  }

  const newTask = {
    title: taskText,
  }; // 새로운 할 일 객체 생성

  fetch(apiUrl, {
    method: "POST", // POST 요청을 보내기
    headers: { "Content-Type": "application/json" }, 
    body: JSON.stringify(newTask), 
  })
    .then((task) => {
      inputField.value = ""; // 입력 필드 비우기기
    })
    .catch((error) => console.error("할 일 추가 실패:", error));
});

// 서버에서 할 일 목록 가져오기
function fetchTasks() {
  fetch(apiUrl)
    .then((response) => response.json())
    .then((tasks) => {
      const taskList = document.querySelector(".task-list");
      taskList.innerHTML = ""; // 기존 목록 초기화

      tasks.forEach((task) => {
        // 1. task div  생성
        const taskElement = document.createElement("div");
        taskElement.classList.add("task");
        taskElement.setAttribute("data-id", task.id); // 데이터베이스 고유 ID를 data-id에 저장
        console.log(task.id);

        // 2. 하위 요소 생성 (아이콘, 입력 필드, 삭제 버튼)
        const imgElement = document.createElement("img");
        imgElement.src = "heart.png"; // 기본 이미지
        imgElement.alt = "빈하트";
        imgElement.classList.add("heart-icon"); // 하트 아이콘 클래스 추가!

        const inputElement = document.createElement("input");
        inputElement.type = "text";
        inputElement.classList.add("task-input");
        inputElement.value = task.title;
        inputElement.readOnly = true;

        const buttonElement = document.createElement("button");
        buttonElement.classList.add("delete-btn");

        const deleteIcon = document.createElement("img");
        deleteIcon.src = "trash_icon.png";
        deleteIcon.alt = "삭제";
        deleteIcon.classList.add("delete-icon");

        // 3. 삭제 버튼에 아이콘 추가
        buttonElement.appendChild(deleteIcon);

        // 4. 하위 요소들 (이미지, 입력 필드, 삭제 버튼)을 taskElement에 추가
        taskElement.appendChild(imgElement);
        taskElement.appendChild(inputElement);
        taskElement.appendChild(buttonElement);

        // 5. 최종적으로 taskList에 taskElement 추가
        taskList.appendChild(taskElement);
      });
    })
    .catch((error) => console.error("할 일 불러오기 실패:", error));
}

// 삭제 버튼에 대한 이벤트 위임 (상위 요소에서 처리)
document
  .querySelector(".task-list")
  .addEventListener("click", function (event) {
    if (
      event.target.classList.contains("delete-btn") ||
      event.target.closest(".delete-btn")
    ) {
      const taskElement = event.target.closest(".task"); // 클릭한 버튼이 속한 task 요소 찾기
      const taskId = taskElement.getAttribute("data-id"); // 해당 task의 고유 ID 가져오기
      deleteTask(taskId); // 삭제할 taskId 전달
    }
  });
// 삭제 요청을 보내는 함수
function deleteTask(taskId) {
  fetch(`http://54.180.87.12:8080/todo/${taskId}`, {
    method: "DELETE", // DELETE 요청
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("삭제 실패");
      }

      window.location.reload();

      console.log("할 일 삭제 성공");
    })
    .catch((error) => {
      console.error("할 일 삭제 실패:", error);
    });
}

// 페이지가 로드되면 바로 할 일 목록을 불러옴
document.addEventListener("DOMContentLoaded", fetchTasks);

// document
//   .querySelector(".task-list")
//   .addEventListener("click", function (event) {
//     // 클릭한 요소가 정확히 'heart-icon' 클래스를 가진 <img> 태그인지 확인
//     if (event.target.matches(".heart-icon")) {
//       const heartImg = event.target;

//       // 이미지가 빈 하트인지 확인 후 변경
//       if (heartImg.src.includes("heart.png")) {
//         heartImg.src = "full heart.png"; // 채워진 하트로 변경
//       } else {
//         heartImg.src = "heart.png"; // 빈 하트로 다시 변경
//       }
//     }
//   });
document
  .querySelector(".task-list")
  .addEventListener("click", function (event) {
    if (event.target.matches(".heart-icon")) {
      const heartImg = event.target;
      const currentSrc = heartImg.src.split("/").pop(); // 파일명만 추출

      if (currentSrc === "heart.png") {
        heartImg.src = "full heart.png"; // 채워진 하트로 변경
      } else {
        heartImg.src = "heart.png"; // 빈 하트로 변경
      }
    }
  });
