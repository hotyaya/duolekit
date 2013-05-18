
	var cm = new Ext.grid.ColumnModel([
		{id:'id',header:'id',dataIndex:'id',hidden:true},
		{header:'img',dataIndex:'img',hidden:true},		
		{header:'创建时间',dataIndex:'ctime',hidden:true},
		{header:'描述',dataIndex:'description',hidden:true},
		{header:'我的N群',dataIndex:'name',menuDisabled:true},
		{header:'在线情况',dataIndex:'currentpeople',menuDisabled:true}
	]);
	
	var store = new Ext.data.Store({   
        proxy : new Ext.data.ScriptTagProxy({url:'group/list.action'}),   
        reader: new Ext.data.JsonReader({
        	//totalProperty: 'totalCount',   
            root: 'groups',         
            id: 'post_id'
            },[   
             	'id','img','ctime','description','name','currentpeople'   
            ])   
        }); 
	store.load();
   //store.load({params:{start:0, limit:10}}); 
   var grouppanel = new Ext.grid.GridPanel({
	    //title:"我的群组",
		cm:cm,		
		store:store,
		viewConfig: {   
            forceFit:true   
		},
		tbar: new Ext.Toolbar({  
			autoWidth:true,  
			autoShow:true,  
			items : [{  
                text : '搜索群组',
                handler: function() {},
                iconCls:'searchgroup'  
            }, {  
                xtype : "tbseparator"  
            }, {  
                text : "退出群组",
                handler: function() {
                	if (grouppanel.getSelectionModel().hasSelection()) {
                		  Ext.Msg.confirm('信息', '确定要退出当前所选群组？', function (btn) {
                              if (btn == 'yes') {
                              		var row = grouppanel.getSelectionModel().getSelected();               		 	  		
                              		Ext.Ajax.request({  				  
										 url:"group/quitgroup.action?",   
										 params:{  				  
										     id:row.get("id")		  
										 },  				 
									 	success: function(resp,opts) { 
							                store.remove(row); //执行删除
									 	}, 
									 	failure: function(resp,opts) { 
									 		Ext.Msg.alert('错误', "服务器出错！！！"); 
									 	} 
									 }); 
                           
                              }else {
                              	
                              }
                		  });
                	}else {
                		 Ext.Msg.alert("错误", "没有群组被选中，无法进行删除操作！");
                	}
                },
                iconCls : "deltegroup"   //图片样式, 需要自己寫css樣式,引入手寫的css,如果用自带会因为浏览器不兼容而不显示图片  
            }]  
		})  
		/*
		bbar: new Ext.PagingToolbar({
			 pageSize: 10,   
			 store: store,   
			 displayInfo: true,   
			 displayMsg: '第{0} 到 {1} 条数据 共{2}条',   
			 emptyMsg: "没有数据"   
		})
		*/
	}); 
   grouppanel.addListener('rowcontextmenu', fnRightClick);

   var rightMenu = new Ext.menu.Menu(
       {
           id: "UserMenu",
           items:
           [
               {
                   id: 'enter',
                   icon: '../images/groupin.png', //图标文件
                   handler: function() {
		                	   if (grouppanel.getSelectionModel().hasSelection()) {
		                	   	   var url = "group/ingroup.action?id="
		                		   var row = grouppanel.getSelectionModel().getSelected();
		                		   url += row.get("id");
		                		   window.open(url);
		                	   }		                		   
                   },
                   text: '进入群组'
               },
               {
                   id: 'property',
                   icon: '../images/groupp.png', //图标文件
                   handler: function() {},
                   text: '群组详情'
               }
           ]
       });
   ///右键菜单
   function fnRightClick(grouppanel, rowIndex, e) {
       grouppanel.getSelectionModel().selectRow(rowIndex);
       e.preventDefault();
       rightMenu.showAt(e.getXY());
   };



	
	
	
	
	
	
	
	