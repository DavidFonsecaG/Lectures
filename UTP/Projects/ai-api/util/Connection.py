import openai
import util.constants as c

class Connection:

    # Set up authentication
    def auth():

        try:
            openai.api_key = c.OPENAI_API_KEY
            print('--> Authentication successfully')

        except Exception as e:
            print("Exception type:", type(e).__name__)
            print("Exception message:", str(e))                      
            print('---> Error connecting to Database.')
            return False
        
        return openai

