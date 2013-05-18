
Ext.onReady(function() {
    //提示
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'; 
	
	  var simple = new Ext.FormPanel({
        labelWidth: '75', // label settings here cascade unless overridden
        frame:true,
        title: "笔记之家",
        bodyStyle:'padding:50px 10px 10px 10px',     
        //height:'100%',
        //width: '100%',
        //defaults: {width: '100%'},
        defaultType: 'textfield',
		standardSubmit: true, //重点,有了它就可以subMit
        items: [{
                fieldLabel: '用户名',
                name: 'user.name',
                allowBlank:false,
                value:'马锦涛',               
                blankText:'请输入用户名!'
            },{
                fieldLabel: '密码',
                name: 'user.password',
                allowBlank:false,
                inputType: 'password',    //密码
                value:'majintao',           
                blankText:'请输入密码!'
            }
        ],
    		  buttons: [{   
                                        text: '登陆',   
                                        type: 'submit',   
                                        //定义表单提交事件   
                                        handler:function(){   
                        if(simple.form.isValid()){//验证合法后使用加载进度条   
                                    Ext.MessageBox.show({   
                                 title: '请稍等',   
                                 msg: '正在加载...', 
                                 progressText: '',   
                                 width:300,   
                                 progress:true,   
                                 closable:false,   
                                 animEl: 'loding' 
                                });   
                                //控制进度速度   
                              var f = function(v){   
                           return function(){   
                               var i = v/11;   
                                Ext.MessageBox.updateProgress(i, '');   
                                  };   
                                };   

                           for(var i = 1; i < 13; i++){   
                             setTimeout(f(i), i*150);   
                              }   
                                     
                               //提交到服务器操作   
                              simple.form.doAction('submit',{   
                              url:'login.action?',
                              method:'post',//提交方法post或get   
                              params:'',   
                              //提交成功的回调函数   
                             success:function(form,action){   
                               if (action.result.msg=='ok') {   
                               //Ext.Msg.alert('提示窗口','提交已成功！');
                              document.location='users/personal.jsp';   
                               
                              
                             } else {   
                                Ext.Msg.alert('登陆错误',action.result.msg);   
                               }   
                               },   
                            //提交失败的回调函数   
                             failure:function(){ 
                              Ext.Msg.alert('错误','服务器出现错误请稍后再试！');   
                                 }   
                               });   
                              }                                                                                  
                                        }   
                                },{   
                                        text: '取消',   
                                        handler:function(){
                                        window.hide();
                                        }
                                },{   
                                    text: '注册',   
                                    handler:function(){
                                    regwindow.show();
                                    }
    }
                              
                                ]
    });
	
    var window = new Ext.Window({
        title: '登录',
        x:'37%',
        y:'38%',
        width: '350',
        height:'350',
        closable:false,
        resizable:false,      
        bodyStyle:'padding:5px;',
        buttonAlign:'center',
        items: simple,
        draggable: false,
        shadow:false  
        
    });


    window.show();
});