	
	var Tree = Ext.tree;
	var treeloader = new Tree.TreeLoader( {
		dataUrl : "users/treepanelload.action"
	});
	
	var treepanel = new Tree.TreePanel( {		
		rootVisible : true, // 设为false将隐藏根节点，很多情况下，我们选择隐藏根节点增加美观性
		border : true, // 有边框
		animate : true, // 动画效果
		autoScroll : true, // 自动滚动
		width:'100%',
		height:parent.height,
		enableDD : true, // 拖拽节点
		containerScroll : true,
		loader : treeloader,
		useArrows : true,
		checkModel : 'cascade', // 对树的级联多选
		onlyLeafCheckable : false
		});
		// 异步加载根节点
		var rootnode = new Tree.AsyncTreeNode( { 
			id : 'p0',
		    text : '我的笔记',
		    draggable : false
		});

		treepanel.setRootNode(rootnode);
		treepanel.on('beforeload', function(node) {
			treepanel.loader.dataUrl = 'users/treepanelload.action?id=' + node.id; 
		});
				
		var contextMenu = new Ext.menu.Menu( {  		  
			items : [ {  		  
			text : '新建笔记',  		  
			handler : newFileHandler  		  
			}, {  		  
			text : '新建文件夹',  
			handler : addFloderHandler  
			}, {  		  
			text : '删除文件夹',  		  
			handler : deleteHandler  		  
			}, {  		  
			text : '重命名',  		  
			handler : modifyHandler  		  
			}]  		  
		});
		
		var fileMenu = new Ext.menu.Menu( {  
			items : [{  			  
			text : '编辑',  			  
			handler : editHandler  			  
			}, {  			  
			text : '查看',  			  
			handler : viewHandler  			  
			}, {
                text: '删除',
                handler : deleteHandler 
			},{
				text: '重命名',
                handler : modifyHandler 
			}]  			  
		});
		
		treepanel.on('contextmenu', treeContextHandler);		  
		function treeContextHandler(node, event) {  	  
			event.preventDefault();	  
			node.select();	
			if(node.leaf) {
				fileMenu.show(node.ui.getAnchor());
				fileMenu.showAt(event.getPoint());
			}else {
				contextMenu.show(node.ui.getAnchor());  			  			
				contextMenu.showAt(event.getPoint());
			} 		  
		} 
		
		/*菜单选项处理函数：新建文件*/
		function newFileHandler() {		
			var newNode = new Ext.tree.TreeNode({  		  
				text : '新建文档',
				leaf: true
			});
			var selectedNode = treepanel.getSelectionModel().getSelectedNode();  	  
			var pid = selectedNode.id;			
			Ext.Ajax.request({  				  
				 url:"users/treehandle!newFile",   
				 params:{  				  
				     title:'新建文档',
				     //flag:'newfile',
				     categoryid:pid			  
				 },  				 
			 	success: function(resp,opts) { 
	                var respText = Ext.util.JSON.decode(resp.responseText);
	                newNode.setId(respText.id);
	                selectedNode.appendChild(newNode);  
			 	}, 
			 	failure: function(resp,opts) { 
			 		var respText = Ext.util.JSON.decode(resp.responseText); 
			 		Ext.Msg.alert('错误', respText.error); 
			 	} 
			 }); 
		}  
		 
		/*菜单选项处理函数：编辑*/
		function editHandler() {  		  
			var selectedNode = treepanel.getSelectionModel().getSelectedNode();
			var id = selectedNode.id;
			var text = selectedNode.text;			
			if(centerRegion.findById("edittab"+id))
			{
				centerRegion.setActiveTab("edittab"+id);
				return ;
			}
			centerRegion.add({
				title:text,
				id:"edittab"+id,
				html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='users/treehandle!editFile?id="+id+"'> </iframe>",
				closable:true
			});
			centerRegion.setActiveTab("edittab"+id);
		}
		/*菜单选项处理函数：新建文件夹*/
		function addFloderHandler() { 
			var newNode = new Ext.tree.TreeNode( {  		  
				text : '新建文件夹',
				leaf: false
			});  		  
			var selectedNode = treepanel.getSelectionModel().getSelectedNode();  	  
			var pid=selectedNode.id;  	
			Ext.Ajax.request({  				  
				 url:"users/treehandle!newFloder",   
				 params:{  				  
				     name:'新建文件夹',
				     //flag:'newfloder',
				     id:pid				 	
				 },  				 
			 	success: function(resp,opts) { 
	                 var respText = Ext.util.JSON.decode(resp.responseText);
	                 newNode.setId(respText.id);
	                 selectedNode.appendChild(newNode); 	
			 	}, 
			 	failure: function(resp,opts) { 
			 		var respText = Ext.util.JSON.decode(resp.responseText); 
			 		Ext.Msg.alert('错误', respText.error); 
			 	}   
		    }); 
				 
		}
		
		
		/*菜单选项处理函数：查看*/
		function viewHandler (){
			var selectedNode = treepanel.getSelectionModel().getSelectedNode(); 
			var id = selectedNode.id;
			var text = selectedNode.text;
			if(centerRegion.findById("viewtab"+id))
			{
				centerRegion.setActiveTab("viewtab"+id);
				return ;
			}
			centerRegion.add({
				title:text,
				id:"viewtab"+id,
				html:"<iframe scrolling='auto' frameborder='0' width='100%' height='100%' src='users/treehandle!viewFile?id="+id+"'> </iframe>",
				closable:true
			});			
			centerRegion.setActiveTab("viewtab"+id);
		}
		/*菜单选项处理函数：删除文件 删除文件夹*/		  
		function deleteHandler() {
			var treenode = treepanel.getSelectionModel().getSelectedNode();
			if(treenode.hasChildNodes()) {
				Ext.Msg.alert('提示', '不能删除非空文件夹');
				return ;
			}
			var nodeid= treenode.id;		  
		  	Ext.Ajax.request({  		  
		  		url:'users/treehandle!delete',  		  
		  		params:{  		  
		  			id:nodeid
		  			//flag:'delete'
				}  		  
		 	});  	  
		  	treepanel.getSelectionModel().getSelectedNode().remove();  		  
		}  
		
		/*菜单选项处理函数：重命名*/
		function modifyHandler() { 
			var treeEditor = new Ext.tree.TreeEditor(treepanel, {			  
				allowBlank : false, // 允许出现空白  				  
				cancelOnEsc : true,// 按下ESC建自动取消空白  				  
				selectOnFocus : true   				  
			}); 		  
			var selectedNode = treepanel.getSelectionModel().getSelectedNode();// 得到选中的节点  		  
			pid=selectedNode.id;  		  		  
			treeEditor.editNode = selectedNode;  		  
			treeEditor.startEdit(selectedNode.ui.textNode);		
			treeEditor.on("complete", function(treeEditor){
				Ext.Ajax.request({  			  
					url:"users/treehandle!modify",  
				  	params:{  
				  			title:treeEditor.editNode.text,  			    
				  			id:pid
				  			//flag:'modify'
				        	}  				  
				 });  			  
			});  		  
		} 
		
		
		
		
//定义并构造西部面板
var westRegion = {
    region:'west',
    id:'west-panel',
    iconCls: 'tabs',
    title:'笔记之家',
    split:true,
    width: 200,
    minSize: 175,
    maxSize: 400,
    collapsible: true,
    margins:'0 0 0 5',
    layout:'column',
    layoutConfig:{
        animate:true
    },
    items: [     
        {
        	items:treepanel
        }
        ]
};