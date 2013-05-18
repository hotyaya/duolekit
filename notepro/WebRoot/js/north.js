//此面板未使用
var northRegion = new Ext.Panel({
    region:'north',   
    xtype: 'panel',
    split: false,
    height:30,
    //html:"<div style='font-size:15pt;'>笔记之家</h1>",
    //defaults: {bodyStyle:'background-image:url(../images/layout-browser-hd-bg.gif)'},
	  bbar:[	  
		  {pressed:false,text:'NoteBook日历',iconCls: 'tabs', 
		  handler: function(){			
					if(centerRegion.findById("calendartab"))
					{
						centerRegion.setActiveTab("calendartab");
						return ;
					}
					centerRegion.add({
						title:"NoteBook日历",
						id:"calendartab",
						html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='users/toolbar/calendar.jsp'> </iframe>",
						closable:true
					});
					centerRegion.setActiveTab("calendartab");
		  		}
		  
		  },
		  {xtype:'tbseparator'},
		  /*
		  {pressed:false,text:'数据通',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'客户管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'人力资源管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'进销存管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:'tbseparator'},
		  {pressed:false,text:'客户管理',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:"tbfill"},		   
		  {pressed:false,text:'刷新',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {pressed:false,text:'帮助',iconCls: 'tabs', handler: function(){alert('预留function');}},
		  {xtype:"combo"}
		  */
		  {xtype:"tbfill"},
		  new Ext.Toolbar.TextItem("您好！<h>xxx</h>"),
		  {pressed:false,text:'退出',iconCls: 'tabs', handler: function()
		  		{
		  			Ext.Ajax.request({  				  
				 		url:"logout.action",   
					 
			 			success: function(resp,opts) {
			 				Ext.MessageBox.confirm("System Info","确认退出吗？",function(btn,txt){
                				if(btn=="yes"){
                    				//Ext.MessageBox.alert("系统提示","系统退出！");
                    				document.location='/';   
                				}else if(btn=="no"){
                    				//Ext.MessageBox.alert("系统提示","你放弃了退出操作！");
                				}
            				});		       
		                	
			 			}, 
			 			failure: function(resp,opts) { 
				 			var respText = Ext.util.JSON.decode(resp.responseText); 
				 			
			 			}   
		    		}); 
		  		}
		   }
		  
		   
	 ]
});