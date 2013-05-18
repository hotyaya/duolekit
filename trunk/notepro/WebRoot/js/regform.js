	Ext.QuickTips.init();											//支持tips提示
　　Ext.form.Field.prototype.msgTarget='side';//提示的方式，枚举值为"qtip","title","under","side",id(元素id)
　　																					//side方式用的较多，右边出现红色感叹号，鼠标上去出现错误提示，其他的我就不介绍了，可自行验证
　　																					//大家可以分别去掉这两行代码，看效果就会明白他们的作用，（放在onReady的function（）{}中）
  	Ext.apply(Ext.form.VTypes,{
　　	password:function(val,field)
          {               						//val指这里的文本框值，field指这个文本框组件，大家要明白这个意思
　　       	if(field.confirmTo)
                 {              //confirmTo是我们自定义的配置参数，一般用来保存另外的组件的id值
　　               var pwd=Ext.get(field.confirmTo);//取得confirmTo的那个id的值
　　               return (val==pwd.getValue());
　　              }
　　              return true;
　　        }
　　});

    var f = new Ext.form.FormPanel({
        title : '填写信息',
        width : 450,
        height : 250,
        bodyStyle : 'padding:20px',
        labelAlign : 'right',
        frame : true,
        items : [
            new Ext.form.TextField({
                name : 'username',
                allowBlank : false,
                blankText : "不能为空，请填写",
                //regex: /^\w+$/, 
                //regexText:"用户名只能由字母和数字组成！", 
                minLength : 2,
                minLengthText : "长度不能小于2个字符",
                maxLength : 15,              
                maxLengthText : "长度超过了15个字符",
                fieldLabel : '用户名',
                width:180
            }),
              {
                name : 'password1',
                id:"pass1",
                xtype : 'textfield',
                inputType : 'password',
                fieldLabel : '密码',
                allowBlank : false,
                blankText : "不能为空，请填写",
                width:180
            },
            {
                name : 'password2',
                id:"pass2",
                xtype : 'textfield',
                inputType : 'password',
                fieldLabel : '确认密码',
                allowBlank : false,
                 blankText : "不能为空，请填写",
                vtype:"password",//自定义的验证类型
                vtypeText:"两次密码不一致！",
　　            confirmTo:"pass1",//要比较的另外一个的组件的id
                width:180
            },
            {
                name : 'email',
                xtype : 'textfield',
                fieldLabel : '邮箱',
                allowBlank : false,
                blankText : "不能为空，请填写",
                vtype:"email",//email格式验证
　　            vtypeText:"不是有效的邮箱地址",
                width:180
            },
            {
                name : 'qq',
                xtype : 'textfield',
                fieldLabel : 'QQ',
                allowBlank : false,
                blankText : "不能为空，请填写",
                width:180
            }
              
        ],
        buttons : [{
            text : '注册',
            handler : function () {
                var username = f.getForm().findField('username').getValue();
                var password = f.getForm().findField('password1').getValue();
                var email = f.getForm().findField('email').getValue();
                var qq = f.getForm().findField('qq').getValue();
                Ext.Ajax.request({
                    	url : 'reg.action',
            		success: function(resp,opts) { 
            			var respText = Ext.util.JSON.decode(resp.responseText); 
                    	var msg = respText.msg;
                        if('ok' == msg) {
                            Ext.MessageBox.alert('提示', '注册成功');
                            regwindow.close();
                        }
                		
                    },
                    failure : function () {
                        Ext.MessageBox.alert('错误', '连接服务器错误！');
                    },
                    method : 'post',
                    params : {name : username, password : password,mailbox : email,qq : qq}
                });
            }
        },{
            text : '重置',
            handler : function () {
                f.getForm().reset();
            }
        }]
    })
    
    
    var regwindow=new Ext.Window({
          title: "笔记之家用户注册", 
          //closable:false,
          modal:true,//桌面阴影
                frame:true,  
                height:280,  
               width:450,
               draggable: false,
               items:[f]
    })