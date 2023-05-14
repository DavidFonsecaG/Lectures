import sys
from util.Connection import Connection

def app():

    # Connet to Openai
    openai = Connection.auth()
    if not(openai):
        sys.exit(1)
    
    # Make a Request to the API
    response = openai.Completion.create(
        engine="davinci",
        prompt="Hello,",
        max_tokens=5
    )

    print(response.choices[0].text)

if __name__ == '__main__':
    app()
