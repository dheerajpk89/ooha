var header = document.getElementById("patientData");
var li = header.getElementsByClassName("li");
for (var i = 0; i < li.length; i++) {
  li[i].addEventListener("click", function() {
  var current = document.getElementsByClassName("active");
  current[0].className = current[0].className.replace(" active", "");
  this.className += " active";
  });
}
