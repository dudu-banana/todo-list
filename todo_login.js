document.addEventListener("DOMContentLoaded", function () {

    // "sign up" 버튼 클릭 시 회원가입 페이지로 이동
    const signupButton = document.querySelector(".signup-btn");
 
    signupButton.addEventListener("click", function () {
        window.location.href = "todo_signup.html";
    });
  
    // "sign in" 버튼 클릭 시 로그인 요청
    const signinButton = document.querySelector(".signin-btn"); // 수정됨
    if (signinButton) {
        signinButton.addEventListener("click", function () {
            alert("버튼 눌림!"); // 버튼 클릭 확인용

            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            const data = {
                email: email,
                password: password
            };

            fetch("13.124.227.234:8080/login", { // 프로토콜 추가
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("로그인 실패");
                }
                return response.json();
            })
            .then(result => {
                console.log("로그인 성공:", result);
                alert("로그인 성공!");
                window.location.href = "todo_main.html"; // 메인 페이지로 이동
            })
            .catch(error => {
                console.error("에러 발생:", error.message);
                alert("로그인 실패: " + error.message);
            });
        });
    } else {
        console.error("signin-btn 버튼을 찾을 수 없습니다!");
    }
});
