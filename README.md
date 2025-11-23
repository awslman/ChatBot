这是我数据结构的课程设计

主要功能包括

mode1：识别命令并作出回应

    命令现有：
        cal - 显示当天的日期，表现与linux系统的cal命令大致相同
        news - 从https://github.com/vikiboss/60s.git中获取精选的15条新闻
    后续可能增加新的命令

mode2：使用NLP工具识别意图并根据意图匹配预设的回答

    包含Stanford CoreNLP、OpenNLP、NLTK

mode3：引入阿里云百炼大模型平台的LLM来生成回答，支持多轮对话，项目开放期间会将apikey写在代码里供用户体验 
    
    LLM在调用时会尝试将对话存储在数据库中
    如果运行环境中装有mysql，可以尝试创建一个ChatBot用户，密码为123456，
    并创建一个数据库chatbot_data，内含一个表app_config,可以使用项目附带的sql文件创建该表
    建议设置ChatBot对chatbot_data的权限为ccreat、select和insert，对app_config表权限仅为select

注：项目停止开放后该仓库可见性会调回private，同时用于该项目的apikey也会被删除
        
