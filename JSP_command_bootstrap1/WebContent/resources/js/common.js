//팝업창들 뛰우기
//새로운 Window창을 Open할 경우 사용되는 함수 ( arg : 주소 , 창타이틀 , 넓이 , 길이 )
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth
							+",height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
	win.focus() ; 
}

//팝업창 닫기
function CloseWindow(parentURL){
	
	window.opener.location.reload(true);		
	window.close();
}


function list_go(page,url){
	if(!url) url="list.do";
	
	var jobForm=$('#jobForm');
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	jobForm.find("[name='searchType']")
		.val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']")
		.val($('div.input-group>input[name="keyword"]').val());
	

	jobForm.attr({action:url,method:'get'}).submit();
}



// summernote
var contextPath="";

function summernote_go(target,context){
	
	contextPath=context;
	
	target.summernote({
		placeholder:'여기에 내용을 적으세요.',
		lang:'ko-KR',
		height:250,
		disableResizeEditor: true,
		callbacks:{
			onImageUpload : function(files, editor, welEditable) {
				for(var file of files){
					//alert(file.name);
					
					if(file.name.substring(file.name.lastIndexOf(".")+1).toUpperCase() != "JPG"){
						alert("JPG 이미지형식만 가능합니다.");
						return;
					}
					if(file.size > 1024*1024*5){
						alert("이미지는 5MB 미만입니다.");
						return;
					}						
				}
				
				for (var file of files) {
					sendFile(file,this);
				}
			},
			onMediaDelete : function(target) {
				//alert(target[0].src);
				deleteFile(target[0].src);	
			}
		}
	});
}

function sendFile(file, el) {
	var form_data = new FormData();
	form_data.append("file", file); 
	
	$.ajax({
		url: contextPath+'/uploadImg.do',
    	data: form_data,
    	type: "POST",	    	
    	contentType: false,	    	
    	processData: false,
    	success: function(img_url) {    		
    		$(el).summernote('editor.insertImage', img_url);
    	},
    	error:function(){
    		alert(file.name+" 업로드에 실패했습니다.");
    	}
	});
}

function deleteFile(src) {		
	var splitSrc= src.split("=");
	var fileName = splitSrc[splitSrc.length-1];
	
	//alert(fileName);
	var fileData = {"fileName":fileName};
	//alert(fileData);
	//alert(JSON.stringify(fileData));
	
	$.ajax({
		url:contextPath+"/deleteImg.do",
		data : JSON.stringify(fileData),
		type:"post",
		contentType:"application/json",
		success:function(res){
			console.log(res);
		},
		error:function(){
			alert("이미지 삭제가 불가합니다.");
		}
	});
	
	
}



//사용자 사진 출력
function MemberPictureThumb(contextPath){
	//var target = document.querySelector('.manPicture');
  	for(var target of document.querySelectorAll('.manPicture')){
  		var id = target.getAttibute('data-id');
  		
  		target.style.backgroundImage="url('"+contextPath+"/member/getPicture.do?id="+id+"')";
  		target.style.backgroundPosition="center";
  		target.style.backgroundRepeat="no-repeat";
  		target.style.backgroundSize="cover";
  	}	
}






