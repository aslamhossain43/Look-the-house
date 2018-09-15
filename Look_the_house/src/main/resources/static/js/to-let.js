//manage active menu
switch (menu) {
case 'About':
	$('#about').addClass('active');
	break;
	
case 'Login':
	$('#login').addClass('active');
	break;
case 'Registration':
	$('#registration').addClass('active');
	break;
	
case 'AddServices':
	$('#addservices').addClass('active');
	break;
	
case 'View-Services':
	$('#viewservices').addClass('active');
	break;
case 'Manage-View-Services':
	$('#manageviewservices').addClass('active');
	break;

	
default:
	     if (menu='Home') {
			
		$('#home').addClass('active');
	     }
		break;
	
}


//adding a pre loader

var preloader=$('.preLoader');
if (preloader.length) {
	
setTimeout(function(){

	$('.preLoader').fadeOut("slow");
	
},2000);

}



var table=$('#viewServices');

if(table.length){

	var jsonUrl=jsonurl;
	

	 table.DataTable({
  lengthMenu:[[30,73,-1],['30 files','73 files','All']],
  pageLength:30,
  ajax:{
	  url:jsonUrl,
	  dataSrc : ''
	 
	  
  },
  columns : [
	  
	  {
		data:'vCode',
		mRender:function(data){
			var str='';
			
			str+='<video src="videos/'+data+'.mp4" id="vsvideo" type="video/mp4"  controls></video>';
			return str;
			
			
			
		}
	  
	  },
		 {
			data:'iCode',
			mRender:function(data){
				var str='';
				
				str+='<img src="images/'+data+'.jpg" id="vsimg" >';
				return str;
				
				
				
			}
		
		
	  },
	  
	  
	  {
		  data:'rentType'
		 
		  
		  
	  },
	  {
		  data:'country'
		 
	  },
	  {
		  data:'district'
		 
	  },
	  {
		  data:'subDistrict'
		 
	  },
	  {
		  data:'detailsAddress'
		 
	  },
	  {
		  data:'serviceDescription'
		 
	  },
	  {
		  data:'contact'
		 
	  }
	  ,
	  {
		  
		  data:'lastModifiedDate'
		 
		 
	  }
	  ,
	  {
		  data:'id',
		  mRender:function(data){
			  				
			  var str='';
					
		
					if (userRole=='ADMIN') {
						
			  str+='<a href="/updateAddservice?id='+data+'" id="editTrauncate" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a>';
                
						
					str+='<a href="/delete?id='+data+'" id="editTrauncate" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></a>';
					return str;
					}if (userRole=='USER') {
						  str+='<a href="/showUpdateWordByUser?id='+data+'" id="editTrauncate" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span></a>';
				             
						return str;
					}
											
			  return str;
			
			  
		  }
		 
	  }
	  
  ]
	
	
	
	
});
} 








//alert message
var alert=$('.alert');
if(alert.length){
	setTimeout(function(){
		
		alert.fadeOut("slow");
		
		
	},15000);
	
}








$("#b").click(function() {
	
	$("p").fadeToggle();
});	




